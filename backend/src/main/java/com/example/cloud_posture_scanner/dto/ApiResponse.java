package com.example.cloud_posture_scanner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {

  private String message;
  private boolean success;
}