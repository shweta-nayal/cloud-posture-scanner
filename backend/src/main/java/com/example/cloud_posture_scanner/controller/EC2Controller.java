package com.example.cloud_posture_scanner.controller;

import com.example.cloud_posture_scanner.model.EC2Instance;
import com.example.cloud_posture_scanner.service.EC2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
class EC2Controller {

  private final EC2Service ec2Service;

  @GetMapping("/instances")
  public List<EC2Instance> getInstances() {
    return ec2Service.getInstances();
  }
}