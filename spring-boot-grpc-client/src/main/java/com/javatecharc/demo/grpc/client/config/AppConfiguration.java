package com.javatecharc.demo.grpc.client.config;

import com.javatecharc.demo.protobuf.grpc.EmployeeServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @GrpcClient("employee-client")
    EmployeeServiceGrpc.EmployeeServiceBlockingStub employeeClientStub;
}
