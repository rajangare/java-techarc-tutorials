package com.javatecharc.demo;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.hazelcast.query.Predicates;
import com.javatecharc.demo.model.Employee;

import java.util.Collection;

public class SQLPredicateExample {
    public static void main(String[] args) {
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
        IMap<Integer, Employee> employeeMap = hazelcastInstance.getMap("employeeMap");

        //Create sample data
        employeeMap.put(101, new Employee(101, "Sachin", "Tendulker", "sachin@abc.com", "1234512345", 10000.0));
        employeeMap.put(102, new Employee(102, "Sourav", "Ganguly", "sourav@abc.com", "5432154321", 20000.0));
        employeeMap.put(103, new Employee(103, "Rohit", "Sharma", "rohit@abc.com", "98766598766", 80000.0));
        employeeMap.put(104, new Employee(104, "MS", "Dhoni", "dhoni@abc.com", "9876655443", 50000.0));
        employeeMap.put(105, new Employee(105, "Sourav", "Singh", "sourav.singh@abc.com", "5432154321", 20000.0));

        System.out.println(employeeMap.values());



        // Equality Predicate (=)
        Collection<Employee> eqOperator = employeeMap.values(Predicates.sql("lastName = Dhoni"));
        System.out.println(eqOperator);

        // Inequality Predicates
        Collection<Employee> inEqOperator = employeeMap.values(Predicates.sql("salary < 50000.0"));
        System.out.println(inEqOperator);

        // AND and OR Predicates
        Collection<Employee> andOperator = employeeMap.values(Predicates.sql("lastName = Ganguly AND salary = 20000.0"));
        System.out.println(andOperator);

        Collection<Employee> regex = employeeMap.values(Predicates.sql("firstName REGEX '^[A-Z][a-z]+$'"));
        System.out.println(regex);



    }
}
