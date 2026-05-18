package com.example.cloud_posture_scanner.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScanResult {

  private String id;

  private String checkName;

  private String status;

  private String evidence;
}