package com.example.cloud_posture_scanner.model;

import lombok.Data;

@Data
public class EC2Instance {

  private String instanceId;
  private String instanceType;
  private String region;
  private String publicIp;
  private String securityGroup;
}