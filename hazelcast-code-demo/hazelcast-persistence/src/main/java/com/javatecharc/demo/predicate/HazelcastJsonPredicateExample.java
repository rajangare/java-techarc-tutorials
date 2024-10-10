package com.javatecharc.demo.predicate;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.HazelcastJsonValue;
import com.hazelcast.map.IMap;
import com.hazelcast.query.Predicates;

public class HazelcastJsonPredicateExample {
    public static void main(String[] args) {
        // Create Hazelcast instance
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();

        // Get a map to store HazelcastJsonValue
        IMap<String, HazelcastJsonValue> employeeMap = hazelcastInstance.getMap("employeeMap");

        // Add some example JSON values
        employeeMap.put("101", new HazelcastJsonValue("{\"empId\":\"101\", \"name\":\"Messi\", \"age\":33, \"salary\":40000, \"address\":{\"country\":\"Argentina\", \"zip\":\"6544321\"}}"));
        employeeMap.put("102", new HazelcastJsonValue("{\"empId\":\"102\", \"name\":\"Rohit\", \"age\":37, \"salary\":10000, \"address\":{\"country\":\"India\", \"zip\":\"567971\"}}"));
        employeeMap.put("103", new HazelcastJsonValue("{\"empId\":\"103\", \"name\":\"Ronaldo\", \"age\":39, \"salary\":20000, \"address\":{\"country\":\"Portugal\", \"zip\":\"543212\"}}"));


        // Query the map using the predicate
        var results = employeeMap.values(Predicates.equal("name", "Ronaldo"));

        // Print the results
        results.forEach(System.out::println);

        var byCountry = employeeMap.values(Predicates.equal("address.country", "Argentina"));
        // Print the results
        byCountry.forEach(System.out::println);

        // Shutdown Hazelcast instance
        hazelcastInstance.shutdown();

    }

}
