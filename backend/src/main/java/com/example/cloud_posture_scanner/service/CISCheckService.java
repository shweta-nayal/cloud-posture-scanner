package com.example.cloud_posture_scanner.service;

import com.example.cloud_posture_scanner.model.CISResult;
import com.example.cloud_posture_scanner.model.S3BucketInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.cloudtrail.CloudTrailClient;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.DescribeSecurityGroupsResponse;
import software.amazon.awssdk.services.ec2.model.IpPermission;
import software.amazon.awssdk.services.ec2.model.IpRange;
import software.amazon.awssdk.services.ec2.model.SecurityGroup;
import software.amazon.awssdk.services.iam.IamClient;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CISCheckService {

  private final S3Service s3Service;
  private final CloudTrailClient cloudTrailClient;
  private final IamClient iamClient;
  private final Ec2Client ec2Client;
  private final DynamoDBService dynamoDBService;

  public List<CISResult> runChecks() {

    List<CISResult> results = new ArrayList<>();

    checkS3Encryption(results);

    checkPublicBuckets(results);

    checkCloudTrail(results);

    checkRootMFA(results);

    checkSecurityGroups(results);

    dynamoDBService.saveResults(results);

    return results;
  }

  private void checkS3Encryption(List<CISResult> results) {

    boolean allEncrypted = s3Service.getBuckets()
        .stream()
        .allMatch(S3BucketInfo::isEncrypted);

    if (allEncrypted) {

      results.add(
          new CISResult(
              "S3 Buckets Encrypted",
              "PASS",
              "All buckets are encrypted"
          )
      );

    } else {

      results.add(
          new CISResult(
              "S3 Buckets Encrypted",
              "FAIL",
              "Some buckets are not encrypted"
          )
      );
    }
  }

  private void checkPublicBuckets(List<CISResult> results) {

    boolean publicBucketExists = s3Service.getBuckets()
        .stream()
        .anyMatch(S3BucketInfo::isPublicAccess);

    if (publicBucketExists) {

      results.add(
          new CISResult(
              "No Public Buckets",
              "FAIL",
              "Public bucket detected"
          )
      );

    } else {

      results.add(
          new CISResult(
              "No Public Buckets",
              "PASS",
              "All buckets are private"
          )
      );
    }
  }

  private void checkCloudTrail(List<CISResult> results) {

    try {

      var response = cloudTrailClient.describeTrails();

      if (!response.trailList().isEmpty()) {

        results.add(
            new CISResult(
                "CloudTrail Enabled",
                "PASS",
                "CloudTrail is enabled"
            )
        );

      } else {

        results.add(
            new CISResult(
                "CloudTrail Enabled",
                "FAIL",
                "No CloudTrail found"
            )
        );
      }

    } catch (Exception e) {

      results.add(
          new CISResult(
              "CloudTrail Enabled",
              "FAIL",
              "Error checking CloudTrail"
          )
      );
    }
  }

  private void checkRootMFA(List<CISResult> results) {

    try {

      var response = iamClient.getAccountSummary();

      Integer mfaEnabled =
          response.summaryMap()
              .get("AccountMFAEnabled");

      if (mfaEnabled != null && mfaEnabled == 1) {

        results.add(
            new CISResult(
                "Root MFA Enabled",
                "PASS",
                "Root account MFA enabled"
            )
        );

      } else {

        results.add(
            new CISResult(
                "Root MFA Enabled",
                "FAIL",
                "Root account MFA not enabled"
            )
        );
      }

    } catch (Exception e) {

      results.add(
          new CISResult(
              "Root MFA Enabled",
              "FAIL",
              "Error checking MFA"
          )
      );
    }
  }

  private void checkSecurityGroups(List<CISResult> results) {

    try {

      DescribeSecurityGroupsResponse response =
          ec2Client.describeSecurityGroups();

      boolean insecureFound = false;

      for (SecurityGroup sg : response.securityGroups()) {

        for (IpPermission permission : sg.ipPermissions()) {

          Integer port = permission.fromPort();

          if (port != null &&
              (port == 22 || port == 3389)) {

            for (IpRange range : permission.ipRanges()) {

              if ("0.0.0.0/0".equals(range.cidrIp())) {

                insecureFound = true;
              }
            }
          }
        }
      }

      if (insecureFound) {

        results.add(
            new CISResult(
                "Security Groups Restricted",
                "FAIL",
                "SSH/RDP open to internet"
            )
        );

      } else {

        results.add(
            new CISResult(
                "Security Groups Restricted",
                "PASS",
                "Security groups safe"
            )
        );
      }

    } catch (Exception e) {

      results.add(
          new CISResult(
              "Security Groups Restricted",
              "FAIL",
              "Error checking security groups"
          )
      );
    }
  }
}
