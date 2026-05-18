package com.example.cloud_posture_scanner.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;

import software.amazon.awssdk.regions.Region;

import software.amazon.awssdk.services.cloudtrail.CloudTrailClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.iam.IamClient;

@Configuration
public class AwsConfig {

  @Value("${aws.accessKey}")
  private String accessKey;

  @Value("${aws.secretKey}")
  private String secretKey;

  @Value("${aws.region}")
  private String region;

  @Bean
  public Ec2Client ec2Client() {

    AwsBasicCredentials awsCredentials =
        AwsBasicCredentials.create(accessKey, secretKey);

    return Ec2Client.builder()
        .region(Region.of(region))
        .credentialsProvider(
            StaticCredentialsProvider.create(awsCredentials)
        )
        .build();
  }

  @Bean
  public CloudTrailClient cloudTrailClient() {

    AwsBasicCredentials awsCredentials =
        AwsBasicCredentials.create(accessKey, secretKey);

    return CloudTrailClient.builder()
        .region(Region.of(region))
        .credentialsProvider(
            StaticCredentialsProvider.create(awsCredentials)
        )
        .build();
  }

  @Bean
  public IamClient iamClient() {

    AwsBasicCredentials awsCredentials =
        AwsBasicCredentials.create(accessKey, secretKey);

    return IamClient.builder()
        .region(Region.AWS_GLOBAL)
        .credentialsProvider(
            StaticCredentialsProvider.create(awsCredentials)
        )
        .build();
  }

  @Bean
  public DynamoDbClient dynamoDbClient() {

    AwsBasicCredentials awsCredentials =
        AwsBasicCredentials.create(accessKey, secretKey);

    return DynamoDbClient.builder()
        .region(Region.of(region))
        .credentialsProvider(
            StaticCredentialsProvider.create(awsCredentials)
        )
        .build();
  }
}