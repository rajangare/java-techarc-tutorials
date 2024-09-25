package com.javatecharc.demo.grpc.bidirectional;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class MessagingServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(15050)
                .addService(new MessagingService())
                .build();

        System.out.println("Starting server on port 15050...");
        server.start();
        server.awaitTermination();
    }
}
