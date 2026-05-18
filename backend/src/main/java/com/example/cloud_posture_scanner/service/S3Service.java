package com.example.cloud_posture_scanner.service;

import com.example.cloud_posture_scanner.model.S3BucketInfo;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;

import software.amazon.awssdk.regions.Region;

import software.amazon.awssdk.services.s3.S3Client;

import software.amazon.awssdk.services.s3.model.Bucket;
import software.amazon.awssdk.services.s3.model.GetBucketEncryptionRequest;
import software.amazon.awssdk.services.s3.model.GetBucketLocationRequest;
import software.amazon.awssdk.services.s3.model.GetBucketPolicyStatusRequest;

import java.util.ArrayList;
import java.util.List;

@Service
public class S3Service {

  @Value("${aws.accessKey}")
  private String accessKey;

  @Value("${aws.secretKey}")
  private String secretKey;

  @Value("${aws.region}")
  private String region;

  private S3Client s3Client;

  @PostConstruct
  public void init() {

    AwsBasicCredentials awsCredentials =
        AwsBasicCredentials.create(
            accessKey,
            secretKey
        );

    s3Client =
        S3Client.builder()
            .region(Region.of(region))
            .credentialsProvider(
                StaticCredentialsProvider.create(
                    awsCredentials
                )
            )
            .build();
  }

  public List<S3BucketInfo> getBuckets() {

    List<S3BucketInfo> bucketList =
        new ArrayList<>();

    try {

      List<Bucket> buckets =
          s3Client.listBuckets().buckets();

      for (Bucket bucket : buckets) {

        S3BucketInfo info =
            new S3BucketInfo();

        String bucketName =
            bucket.name();

        info.setBucketName(bucketName);

        // REGION

        String bucketRegion;

        try {

          bucketRegion =
              s3Client.getBucketLocation(
                  GetBucketLocationRequest
                      .builder()
                      .bucket(bucketName)
                      .build()
              ).locationConstraintAsString();

          if (bucketRegion == null
              || bucketRegion.isEmpty()) {

            bucketRegion = "us-east-1";
          }

        } catch (Exception e) {

          bucketRegion = "unknown";
        }

        info.setRegion(bucketRegion);

        // ENCRYPTION CHECK

        try {

          s3Client.getBucketEncryption(
              GetBucketEncryptionRequest
                  .builder()
                  .bucket(bucketName)
                  .build()
          );

          info.setEncrypted(true);

        } catch (Exception e) {

          info.setEncrypted(false);
        }

        // PUBLIC ACCESS CHECK

        try {

          boolean isPublic =
              s3Client.getBucketPolicyStatus(
                      GetBucketPolicyStatusRequest
                          .builder()
                          .bucket(bucketName)
                          .build()
                  )
                  .policyStatus()
                  .isPublic();

          info.setPublicAccess(isPublic);

        } catch (Exception e) {

          info.setPublicAccess(false);
        }

        bucketList.add(info);
      }

    } catch (Exception e) {

      e.printStackTrace();
    }

    return bucketList;
  }
}