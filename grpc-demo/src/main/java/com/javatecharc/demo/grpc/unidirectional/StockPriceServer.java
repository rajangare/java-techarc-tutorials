package com.javatecharc.demo.grpc.unidirectional;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class StockPriceServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(18090)
                .addService(new StockPriceService())
                .build();

        System.out.println("Starting server on port 18090...");
        server.start();
        server.awaitTermination();
    }
}
