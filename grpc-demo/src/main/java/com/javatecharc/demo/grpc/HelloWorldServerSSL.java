package com.javatecharc.demo.grpc;

import com.javatecharc.demo.grpc.service.HelloWorldService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.File;
import java.io.IOException;

public class HelloWorldServerSSL {
    private final Server server;

    public HelloWorldServerSSL(int port) throws IOException {
        //TODO : SSL Configuration on gRPC Server side
        server = ServerBuilder.forPort(8443)
                .useTransportSecurity(new File("server.crt"), new File("server.pem"))
                .addService(new HelloWorldService())
                .build();

        server.start();

    }

    public void start() throws IOException {
        server.start();
        System.out.println("GRPC Server started on port " + server.getPort());
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** Shutting down gRPC server since JVM is shutting down");
            HelloWorldServerSSL.this.stop();
            System.err.println("*** Server shut down");
        }));
    }

    public void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        HelloWorldServerSSL server = new HelloWorldServerSSL(8090);
        server.start();
        server.blockUntilShutdown();
    }
}
