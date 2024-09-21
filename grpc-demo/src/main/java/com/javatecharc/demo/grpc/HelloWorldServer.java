package com.javatecharc.demo.grpc;

import com.javatecharc.demo.grpc.service.HelloWorldService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class HelloWorldServer {
    private final Server server;

    public HelloWorldServer(int port) {
        server = ServerBuilder.forPort(port)
                .addService(new HelloWorldService())
                .build();
    }

    public void start() throws IOException {
        server.start();
        System.out.println("GRPC Server started on port " + server.getPort());
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** Shutting down gRPC server since JVM is shutting down");
            HelloWorldServer.this.stop();
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
        HelloWorldServer server = new HelloWorldServer(8090);
        server.start();
        server.blockUntilShutdown();
    }
}
