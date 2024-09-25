package com.javatecharc.demo.grpc.bidirectional;

import com.javatecharc.grpc.proto.message.MessagingServiceGrpc;
import com.javatecharc.grpc.proto.message.SampleMessage;
import io.grpc.stub.StreamObserver;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MessagingService extends MessagingServiceGrpc.MessagingServiceImplBase {
    @Override
    public StreamObserver<SampleMessage> sendMessage(StreamObserver<SampleMessage> responseObserver) {
        return new StreamObserver<>() {

            @Override
            public void onNext(SampleMessage clientMessage) {
                System.out.println("Received from client: " + clientMessage.getSender() + ": " + clientMessage.getMessage());

                // Respond with an acknowledgment
                String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                SampleMessage response = SampleMessage.newBuilder()
                        .setSender("Grpc Server")
                        .setMessage("Message received: " + clientMessage.getMessage())
                        .setTimestamp(timeStamp)
                        .build();
                responseObserver.onNext(response);
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Error: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }
}
