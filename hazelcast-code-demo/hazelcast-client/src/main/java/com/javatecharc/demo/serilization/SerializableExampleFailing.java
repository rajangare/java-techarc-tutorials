package com.javatecharc.demo.serilization;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

public class SerializableExampleFailing {
    public static void main(String... args) {
        //initialize hazelcast server/instance
        HazelcastInstance hazelcast = Hazelcast.newHazelcastInstance();
        //create a set to track employees
        IMap<Employee, String> employeeOwners = hazelcast.getMap("empDataMap");
        Employee employee = new Employee("JavaTechARC 3i", "javatecharc3i@gmail.com", "123456789");

        // add employee to map
        System.out.println("Serializing key-value and add to map");
        employeeOwners.put(employee, "test demo");
        Employee employee1 = new Employee("JavaTechARC 3i", "javatecharc3i@gmail.com", "987654321");
        // check if emp1 is present in the set
        System.out.println("Checking if employee with JavaTechARC 3i is present");
        System.out.println(employeeOwners.containsKey(employee1));


        Employee employee2 = new Employee("JavaTechARC 3i", "javatecharc3i@gmail.com", "123456789");
        System.out.println("Checking if employee with JavaTechARC 3i is present");
        System.out.println(employeeOwners.containsKey(employee2));

        // perform a graceful shutdown
        hazelcast.shutdown();
    }
}
