package com.javatecharc.demo.grpc.service;

import com.javatecharc.grpc.GreetingRequest;
import com.javatecharc.grpc.GreetingResponse;
import com.javatecharc.grpc.GreetingServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {

    @Override
    public void sayHello(GreetingRequest request, StreamObserver<GreetingResponse> responseObserver) {
        String name = request.getName();
        String message = "Hello, " + name + "! Welcome to gRPC with Spring Boot.";

        GreetingResponse response = GreetingResponse.newBuilder()
                .setMessage(message)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
