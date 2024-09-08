package com.javatecharc.demo.serilization;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

public class SerializableExamplePassing {
    public static void main(String... args) {
        //initialize hazelcast server/instance
        HazelcastInstance hazelcast = Hazelcast.newHazelcastInstance();
        //create a set to track employees
        IMap<EmployeeExternalizable, String> employeeOwners = hazelcast.getMap("empDataMap");
        EmployeeExternalizable employee = new EmployeeExternalizable("JavaTechARC 3i", "javatecharc3i@gmail.com", "123456789");

        // add employee to map
        System.out.println("Serializing key-value and add to map");
        employeeOwners.put(employee, "test demo");
        EmployeeExternalizable employee1 = new EmployeeExternalizable("JavaTechARC 3i", "javatecharc3i@gmail.com", "987654321");
        // check if emp1 is present in the set
        System.out.println("Checking if employee with JavaTechARC 3i is present");
        System.out.println(employeeOwners.containsKey(employee1));


        EmployeeExternalizable employee2 = new EmployeeExternalizable("JavaTechARC 3i", "javatecharc3i@gmail.com", "123456789");
        System.out.println("Checking if employee with JavaTechARC 3i is present");
        System.out.println(employeeOwners.containsKey(employee2));

        // perform a graceful shutdown
        hazelcast.shutdown();
    }
}
