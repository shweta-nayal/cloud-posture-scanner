package com.example.cloud_posture_scanner.controller;

import com.example.cloud_posture_scanner.dto.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

  @GetMapping("/test")
  public ApiResponse test() {
    return new ApiResponse(
        "Backend Working",
        true
    );
  }
}