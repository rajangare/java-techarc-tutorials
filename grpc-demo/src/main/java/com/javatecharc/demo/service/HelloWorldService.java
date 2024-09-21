package com.javatecharc.demo.service;

import com.javatecharc.demo.proto.HelloRequest;
import com.javatecharc.demo.proto.HelloResponse;
import com.javatecharc.demo.proto.HelloWorldGrpc;
import io.grpc.stub.StreamObserver;

public class HelloWorldService extends HelloWorldGrpc.HelloWorldImplBase {
    @Override
    public void helloJavaTechArc(HelloRequest req, StreamObserver<HelloResponse> responseObserver) {
        HelloResponse reply = HelloResponse.newBuilder().setMessage("Hello " + req.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
