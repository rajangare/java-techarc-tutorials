package com.javatecharc.demo.serilization;

import com.hazelcast.config.Config;
import com.hazelcast.config.SerializerConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

public class HazelcastSerializableTest {
    public static void main(String[] args) {
        Config config = new Config();
        SerializerConfig serializerConfig = new SerializerConfig()
                .setTypeClass(Employee.class)
                .setImplementation(new EmployeeCustomSerializer());
        config.getSerializationConfig().addSerializerConfig(serializerConfig);

        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);

        IMap<Employee, String> employeeOwners = hazelcastInstance.getMap("empDataMap");
        Employee employee = new Employee(123, "JavaTechARC 3i", "javatecharc3i@gmail.com", "9830283");

        System.out.println("Serializing key-value and add to map");
        employeeOwners.put(employee, "Demo Test");

        System.out.println("Serializing key for searching and Deserializing");
        System.out.println(employeeOwners.get(employee));


        hazelcastInstance.shutdown();
    }
}
