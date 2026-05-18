package com.example.cloud_posture_scanner.service;

import com.example.cloud_posture_scanner.model.CISResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DynamoDBService {

  private final DynamoDbClient dynamoDbClient;

  private static final String TABLE_NAME =
      "cloud-posture-results";

  public void saveResults(List<CISResult> results) {

    for (CISResult result : results) {

      Map<String, AttributeValue> item =
          new HashMap<>();

      item.put(
          "id",
          AttributeValue.builder()
              .s(UUID.randomUUID().toString())
              .build()
      );

      item.put(
          "checkName",
          AttributeValue.builder()
              .s(result.getCheckName())
              .build()
      );

      item.put(
          "status",
          AttributeValue.builder()
              .s(result.getStatus())
              .build()
      );

      item.put(
          "evidence",
          AttributeValue.builder()
              .s(result.getEvidence())
              .build()
      );

      PutItemRequest request =
          PutItemRequest.builder()
              .tableName(TABLE_NAME)
              .item(item)
              .build();

      dynamoDbClient.putItem(request);
    }
  }
}
