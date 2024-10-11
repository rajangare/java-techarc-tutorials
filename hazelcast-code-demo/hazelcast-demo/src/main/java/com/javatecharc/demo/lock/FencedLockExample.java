package com.javatecharc.demo.lock;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.cp.lock.FencedLock;

public class FencedLockExample {
    public static void main(String[] args) {
        // Start a Hazelcast instance
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();

        // Obtain a FencedLock instance
        FencedLock lock = hazelcastInstance.getCPSubsystem().getLock("my-distributed-lock");

        // Acquire the lock
        long fencingToken = lock.lockAndGetFence();
        System.out.println("Lock acquired with fencing token: " + fencingToken);

        try {
            // Critical section: protected by the lock
            performCriticalOperation();

        } finally {
            // Release the lock
            lock.unlock();
            System.out.println("Lock released");
        }

        // Shutdown Hazelcast instance
        hazelcastInstance.shutdown();
    }

    public static void performCriticalOperation() {
        // Simulate some work
        System.out.println("Performing a critical operation...");
    }
}
