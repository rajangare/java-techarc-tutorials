package com.javatecharc.demo.grpc.client;

import com.javatecharc.demo.protobuf.grpc.EmployeeRequest;
import com.javatecharc.demo.protobuf.grpc.EmployeeResponse;
import com.javatecharc.demo.protobuf.grpc.EmployeeServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClientDemo {

    public static void main(String[] args) {
        // This is where you would implement the gRPC client logic
        // to connect to the gRPC server and make requests.
        System.out.println("gRPC Client is running...");

        // Example: Create a channel, stub, and make a request
        // (Implementation details would go here)

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8090)
                .usePlaintext()
                .build();

        EmployeeServiceGrpc.EmployeeServiceBlockingStub stub = EmployeeServiceGrpc.newBlockingStub(channel);

        EmployeeRequest request = EmployeeRequest.newBuilder().setEmpId(103).build();
        EmployeeResponse response = stub.getEmployeeDetails(request);

        System.out.println(response);
        channel.shutdown();
    }
}
