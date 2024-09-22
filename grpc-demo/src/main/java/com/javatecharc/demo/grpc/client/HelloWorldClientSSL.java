package com.javatecharc.demo.grpc.client;

import com.javatecharc.demo.proto.HelloRequest;
import com.javatecharc.demo.proto.HelloResponse;
import com.javatecharc.demo.proto.HelloWorldGrpc;
import io.grpc.ManagedChannel;
import io.grpc.netty.shaded.io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import io.grpc.netty.shaded.io.netty.handler.ssl.SslContext;

import javax.net.ssl.SSLException;
import java.io.File;

public class HelloWorldClientSSL {
    public static void main(String[] args) throws SSLException {
        // Path to the server's trusted certificate
        File serverCert = new File("path/to/server.crt");

        // Create SSL context using the server's certificate
        SslContext sslContext = GrpcSslContexts.forClient()
                .trustManager(serverCert)
                .build();

        ManagedChannel managedChannel = NettyChannelBuilder.forAddress("localhost", 8443)
                .sslContext(sslContext)
                .maxInboundMessageSize(20 * 1024 * 1024)
                .build();

        HelloWorldGrpc.HelloWorldBlockingStub stub = HelloWorldGrpc.newBlockingStub(managedChannel);

        HelloResponse response = stub.helloJavaTechArc(HelloRequest.newBuilder().setName("Java TechARC 3i").build());
        System.out.println("Response from server: " + response.getMessage());

        managedChannel.shutdown();
    }
}
