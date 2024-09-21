package com.javatecharc.demo.grpc.service;

import com.javatecharc.demo.proto.HelloRequest;
import com.javatecharc.demo.proto.HelloResponse;
import com.javatecharc.demo.proto.HelloWorldGrpc;
import io.grpc.stub.StreamObserver;

public class HelloWorldService extends HelloWorldGrpc.HelloWorldImplBase {
    @Override
    public void helloJavaTechArc(HelloRequest req, StreamObserver<HelloResponse> responseObserver) {
        HelloResponse response = HelloResponse.newBuilder().setMessage("Welcome to '"+req.getName()+"' GRPC Hello World Demo!" ).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
