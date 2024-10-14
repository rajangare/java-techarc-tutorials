package com.javatecharc.demo.pncounter;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.crdt.pncounter.PNCounter;

public class PNCounterExample {
    public static void main(String[] args) {
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
        PNCounter counter = hazelcastInstance.getPNCounter("javatecharc-pn-counter");

        // Increment the counter
        counter.incrementAndGet();
        System.out.println("Increment Current counter value: " + counter.get());
        counter.incrementAndGet();
        System.out.println("Increment Current counter value: " + counter.get());
        counter.incrementAndGet();
        System.out.println("Increment Current counter value: " + counter.get());

        // Decrement the counter
        counter.decrementAndGet();
        System.out.println("Decrement Current counter value: " + counter.get());

        // Decrement the counter
        counter.decrementAndGet();
        System.out.println("Decrement Current counter value: " + counter.get());

        // Decrement the counter
        counter.decrementAndGet();
        System.out.println("Decrement Current counter value: " + counter.get());

        // Get the current value
        long value = counter.get();
        System.out.println("Current counter value: " + value);

        hazelcastInstance.shutdown();
    }
}
