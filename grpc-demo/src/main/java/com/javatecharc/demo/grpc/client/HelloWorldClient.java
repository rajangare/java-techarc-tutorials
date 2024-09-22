package com.javatecharc.demo.grpc.client;

import com.javatecharc.demo.proto.HelloRequest;
import com.javatecharc.demo.proto.HelloResponse;
import com.javatecharc.demo.proto.HelloWorldGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import javax.net.ssl.SSLException;

public class HelloWorldClient {
    public static void main(String[] args) throws SSLException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8090)
                .usePlaintext()
                .build();

        HelloWorldGrpc.HelloWorldBlockingStub stub = HelloWorldGrpc.newBlockingStub(channel);

        HelloResponse response = stub.helloJavaTechArc(HelloRequest.newBuilder().setName("Java TechARC 3i").build());
        System.out.println("Response from server: " + response.getMessage());

        channel.shutdown();
    }
}
