package com.javatecharc.demo.grpc.client;

import com.javatecharc.demo.grpc.client.service.EmployeeServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrpcClientApplication implements CommandLineRunner {

    @Autowired
    private EmployeeServiceClient employeeServiceClient;

    public static void main(String[] args) {
        SpringApplication.run(GrpcClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        employeeServiceClient.getEmployeeDetails(101);
    }
}
