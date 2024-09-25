package com.javatecharc.demo.grpc.unidirectional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.javatecharc.grpc.proto.stock.*;
import com.javatecharc.grpc.proto.stock.StockPriceServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StockPriceService extends StockPriceServiceGrpc.StockPriceServiceImplBase {

    @Override
    public void streamStockPrices(StockRequest request, StreamObserver<StockPriceResponse> responseObserver) {
        String stockSymbol = request.getStockSymbol();
        Random random = new Random();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (int i = 0; i < 10; i++) { // Stream 10 stock prices
            double price = 100 + (random.nextDouble() * 50); // Random price between 100 and 150
            String timestamp = formatter.format(new Date());

            StockPriceResponse stockPrice = StockPriceResponse.newBuilder()
                    .setStockSymbol(stockSymbol)
                    .setPrice(price)
                    .setTimestamp(timestamp)
                    .build();

            // Send the price to the client
            responseObserver.onNext(stockPrice);

            try {
                Thread.sleep(1000); // Simulate time interval
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }

        responseObserver.onCompleted(); // Close the stream when done
    }
}