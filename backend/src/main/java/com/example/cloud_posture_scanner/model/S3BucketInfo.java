package com.example.cloud_posture_scanner.model;

import lombok.Data;

@Data
public class S3BucketInfo {

  private String bucketName;
  private String region;
  private boolean encrypted;
  private boolean publicAccess;
}