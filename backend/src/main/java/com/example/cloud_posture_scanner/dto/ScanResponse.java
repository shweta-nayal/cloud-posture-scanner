package com.example.cloud_posture_scanner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ScanResponse {

  private String message;

  private int totalInstances;

  private int totalBuckets;

  private int totalChecks;

  private String scanTime;
}