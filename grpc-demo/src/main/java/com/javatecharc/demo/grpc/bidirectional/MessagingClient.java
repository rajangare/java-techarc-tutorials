package com.javatecharc.demo.grpc.bidirectional;

import com.javatecharc.grpc.proto.message.MessagingServiceGrpc;
import com.javatecharc.grpc.proto.message.SampleMessage;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MessagingClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 15050)
                .usePlaintext()
                .build();

        MessagingServiceGrpc.MessagingServiceStub asyncStub = MessagingServiceGrpc.newStub(channel);

        StreamObserver<SampleMessage> requestObserver = asyncStub.sendMessage(new StreamObserver<>() {
            @Override
            public void onNext(SampleMessage serverMessage) {
                System.out.println("Received from server: " + serverMessage.getSender() + ": " + serverMessage.getMessage());
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Error: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Chat completed.");
                channel.shutdown();
            }
        });

        // Simulate chat by sending messages from client to server
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 5; i++) {
            System.out.print("Enter your message: ");
            String message = scanner.nextLine();
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            SampleMessage sampleMessage = SampleMessage.newBuilder()
                    .setSender("JavaTechARC")
                    .setMessage(message)
                    .setTimestamp(timeStamp)
                    .build();

            requestObserver.onNext(sampleMessage);
        }

        requestObserver.onCompleted(); // End the chat after sending messages
    }
}
