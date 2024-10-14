package com.javatecharc.demo.generator;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.flakeidgen.FlakeIdGenerator;

public class FlakIdGeneratorExample {
    public static void main(String[] args) {
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
        FlakeIdGenerator flakeIdGenerator = hazelcastInstance.getFlakeIdGenerator("javatecharc-flake-id-generator");

        // Generate a unique ID
        long uniqueId = flakeIdGenerator.newId();
        System.out.println("Generated ID: " + uniqueId);

        hazelcastInstance.shutdown();
    }
}
