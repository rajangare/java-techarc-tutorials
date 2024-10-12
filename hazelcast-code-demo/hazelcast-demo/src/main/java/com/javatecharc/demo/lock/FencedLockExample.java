package com.javatecharc.demo.lock;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.cp.lock.FencedLock;

public class FencedLockExample {
    public static void main(String[] args) {
        // Start a Hazelcast instance
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();

        // Get a FencedLock from the CP Subsystem
        FencedLock fencedLock = hazelcastInstance.getCPSubsystem().getLock("java-tech-arc-lock-demo");

        // Acquire the lock and get the fencing token
        long token = fencedLock.lockAndGetFence();
        System.out.println(fencedLock.getServiceName());
        try {
            System.out.println("Lock acquired with token: " + token);

            // Critical section of the code
            performCriticalOperation(token);

        } finally {
            // Release the lock
            fencedLock.unlock();
            System.out.println("Lock released.");
        }

        // Shutdown the Hazelcast instance
        hazelcastInstance.shutdown();
    }

    private static void performCriticalOperation(long token) {
        // Example of a critical operation that uses the fencing token for validation
        System.out.println("Performing a critical operation with token: " + token);
        // Your business logic goes here
    }
}
