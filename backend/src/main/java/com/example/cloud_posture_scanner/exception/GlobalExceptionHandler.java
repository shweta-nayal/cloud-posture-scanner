package com.example.cloud_posture_scanner.exception;

import com.example.cloud_posture_scanner.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ErrorResponse handleException(Exception ex) {

    return new ErrorResponse(
        ex.getMessage(),
        HttpStatus.INTERNAL_SERVER_ERROR.value()
    );
  }
}
