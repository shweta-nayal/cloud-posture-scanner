package com.example.cloud_posture_scanner.controller;

import com.example.cloud_posture_scanner.dto.ScanResponse;
import com.example.cloud_posture_scanner.service.ScanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScanController {

  private final ScanService scanService;

  @PostMapping("/scan")
  public ScanResponse scan() {

    return scanService.runScan();
  }
}
