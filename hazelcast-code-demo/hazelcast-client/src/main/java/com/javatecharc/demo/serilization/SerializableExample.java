package com.javatecharc.demo.serilization;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import java.util.Map;

public class SerializableExample {
    public static void main(String[] args) {

        HazelcastInstance hazelcast = Hazelcast.newHazelcastInstance();

        IMap<Employee, String> employeeOwners = hazelcast.getMap("empDataMap");
        Employee employee = new Employee(123, "JavaTechARC 3i", "javatecharc3i@gmail.com", "9830283");

        System.out.println("Serializing key-value and add to map");
        employeeOwners.put(employee, "Demo Test");

        System.out.println("Serializing key for searching and Deserializing");
        System.out.println(employeeOwners.get(employee));


        hazelcast.shutdown();
    }
}
