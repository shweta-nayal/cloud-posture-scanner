package com.example.cloud_posture_scanner.controller;

import com.example.cloud_posture_scanner.model.S3BucketInfo;
import com.example.cloud_posture_scanner.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class S3Controller {

  private final S3Service s3Service;

  @GetMapping("/buckets")
  public List<S3BucketInfo> getBuckets() {
    return s3Service.getBuckets();
  }
}
