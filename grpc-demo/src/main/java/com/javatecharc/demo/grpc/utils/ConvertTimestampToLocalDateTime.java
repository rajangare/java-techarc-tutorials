package com.javatecharc.demo.grpc.utils;

import com.google.protobuf.Timestamp;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class ConvertTimestampToLocalDateTime {
    public static LocalDateTime convertToLocalDateTime(Timestamp timestamp) {
        Instant instant = Instant.ofEpochSecond(timestamp.getSeconds(), timestamp.getNanos());
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    public static void main(String[] args) {
        // Example Protobuf Timestamp
        Timestamp timestamp = Timestamp.newBuilder()
                .setSeconds(1727440895) // Example seconds
                .setNanos(454746300) // Example nanoseconds
                .build();

        System.out.println("Timestamp : "+timestamp);

        LocalDateTime localDateTime = convertToLocalDateTime(timestamp);
        System.out.println("LocalDateTime: " + localDateTime);
    }
}
