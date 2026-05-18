package com.example.cloud_posture_scanner.service;

import com.example.cloud_posture_scanner.dto.ScanResponse;
import com.example.cloud_posture_scanner.model.CISResult;
import com.example.cloud_posture_scanner.model.EC2Instance;
import com.example.cloud_posture_scanner.model.S3BucketInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScanService {

  private final EC2Service ec2Service;

  private final S3Service s3Service;

  private final CISCheckService cisCheckService;

  public ScanResponse runScan() {

    List<EC2Instance> instances =
        ec2Service.getInstances();

    List<S3BucketInfo> buckets =
        s3Service.getBuckets();

    List<CISResult> checks =
        cisCheckService.runChecks();

    return new ScanResponse(
        "Scan Completed Successfully",
        instances.size(),
        buckets.size(),
        checks.size(),
        LocalDateTime.now().toString()
    );
  }
}
