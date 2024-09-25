package com.javatecharc.demo.grpc.unidirectional;

import com.javatecharc.grpc.proto.stock.StockPriceResponse;
import com.javatecharc.grpc.proto.stock.StockPriceServiceGrpc;
import com.javatecharc.grpc.proto.stock.StockRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StockPriceClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 18090)
                .usePlaintext()
                .build();

        StockPriceServiceGrpc.StockPriceServiceStub asyncStub = StockPriceServiceGrpc.newStub(channel);

        StockRequest request = StockRequest.newBuilder()
                .setStockSymbol("JAVATECHARC")
                .build();

        StreamObserver<StockPriceResponse> responseObserver = new StreamObserver<>() {
            @Override
            public void onNext(StockPriceResponse value) {
                System.out.println("Received stock price: " +
                        value.getStockSymbol() + " $" +
                        value.getPrice() + " at " +
                        value.getTimestamp());
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Error: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Stream completed.");
                channel.shutdown();
            }
        };

        asyncStub.streamStockPrices(request, responseObserver);

        try {
            Thread.sleep(12000); // Wait for streaming to finish
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
    }
}
