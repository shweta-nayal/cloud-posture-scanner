package com.example.cloud_posture_scanner.controller;

import com.example.cloud_posture_scanner.model.CISResult;
import com.example.cloud_posture_scanner.service.CISCheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CISController {

  private final CISCheckService cisCheckService;

  @GetMapping("/cis-results")
  public List<CISResult> getResults() {

    return cisCheckService.runChecks();
  }
}
