package com.example.cloud_posture_scanner.service;

import com.example.cloud_posture_scanner.model.EC2Instance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.*;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EC2Service {

  private final Ec2Client ec2Client;

  public List<EC2Instance> getInstances() {

    List<EC2Instance> instanceList = new ArrayList<>();

    DescribeInstancesResponse response =
        ec2Client.describeInstances();

    for (Reservation reservation : response.reservations()) {

      for (Instance awsInstance : reservation.instances()) {

        EC2Instance instance = new EC2Instance();

        instance.setInstanceId(
            awsInstance.instanceId()
        );

        instance.setInstanceType(
            awsInstance.instanceTypeAsString()
        );

        instance.setRegion("ap-south-1");

        instance.setPublicIp(
            awsInstance.publicIpAddress()
        );

        if (!awsInstance.securityGroups().isEmpty()) {

          instance.setSecurityGroup(
              awsInstance.securityGroups()
                  .get(0)
                  .groupName()
          );
        }

        instanceList.add(instance);
      }
    }

    return instanceList;
  }
}