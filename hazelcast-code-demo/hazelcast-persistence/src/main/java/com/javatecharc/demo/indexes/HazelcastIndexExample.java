package com.javatecharc.demo.indexes;

import com.hazelcast.config.IndexType;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.javatecharc.demo.model.User;

public class HazelcastIndexExample {
    public static void main(String[] args) {
        // Create a Hazelcast instance
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();

        // Get the distributed map
        IMap<String, User> userMap = hazelcastInstance.getMap("users");

        // Add a hash index on the "name" field
        userMap.addIndex(IndexType.HASH, "name");  // false indicates a hash index

        // Add a sorted index on the "age" field
        userMap.addIndex(IndexType.SORTED, "age");    // true indicates a sorted index
        hazelcastInstance.shutdown();
    }
}
