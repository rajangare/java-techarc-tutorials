package com.javatecharc.demo.grpc.client.service;

import com.javatecharc.demo.protobuf.grpc.EmployeeRequest;
import com.javatecharc.demo.protobuf.grpc.EmployeeResponse;
import com.javatecharc.demo.protobuf.grpc.EmployeeServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

@Component
public class EmployeeServiceClient {
    @GrpcClient("employeeService")
    EmployeeServiceGrpc.EmployeeServiceBlockingStub stub;

    public EmployeeResponse getEmployeeDetails(int empId) {
        EmployeeRequest request = EmployeeRequest.newBuilder().setEmpId(103).build();
        EmployeeResponse response = stub.getEmployeeDetails(request);

        System.out.println(response);
        // You can add additional logic here if needed, such as error handling or logging.

        return response;
    }
}
