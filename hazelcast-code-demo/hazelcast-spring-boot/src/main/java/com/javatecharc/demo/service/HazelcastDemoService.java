package com.javatecharc.demo.service;

import com.hazelcast.collection.IQueue;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class HazelcastDemoService {
    public final HazelcastInstance hazelcastInstance;

    public HazelcastDemoService(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    /**
     * Consume this demo method from controller or any other layer as required
     */
    public String hazelcastDemo() {
        IMap<String, String> testMap = hazelcastInstance.getMap("javaTechARC_Map");
        System.out.println("Size before add : "+testMap.size());

        testMap.put("demKey", "JavaTechARC3i");
        System.out.println("Size add add : "+testMap.size());
        System.out.println("Map Data : "+testMap);

        IQueue<String> iQueue = hazelcastInstance.getQueue("employee_queue");
        iQueue.add("Test");
        iQueue.remove("Test");

        return "Success";
    }

    @Cacheable("demo")
    public String cacheableDemo(String key) {
        // Simulate a time-consuming operation
        try {
            Thread.sleep(3000); // Simulate delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return "demo cache : "+key;
    }
}
