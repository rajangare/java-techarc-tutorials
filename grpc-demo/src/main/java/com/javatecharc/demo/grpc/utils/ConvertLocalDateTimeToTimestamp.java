package com.javatecharc.demo.grpc.utils;

import com.google.protobuf.Timestamp;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class ConvertLocalDateTimeToTimestamp {
    public static Timestamp convertToTimestamp(LocalDateTime localDateTime) {
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Timestamp.newBuilder()
                .setSeconds(instant.getEpochSecond())
                .setNanos(instant.getNano())
                .build();
    }

    public static void main(String[] args) {
        // Example LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println("Local DateTime : "+localDateTime);

        Timestamp timestamp = convertToTimestamp(localDateTime);
        System.out.println("Protobuf Timestamp: " + timestamp);
    }
}