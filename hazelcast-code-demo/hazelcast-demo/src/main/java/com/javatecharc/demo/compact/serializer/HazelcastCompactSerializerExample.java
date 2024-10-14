package com.javatecharc.demo.compact.serializer;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.javatecharc.demo.model.Student;

public class HazelcastCompactSerializerExample {
    public static void main(String[] args) {
        Config config = new Config();
        config.getSerializationConfig().getCompactSerializationConfig()
                .addSerializer(new StudentCompactSerializer());
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);

        IMap<Integer, Student> studentMap = hazelcastInstance.getMap("students");

        studentMap.put(101, new Student(101, "Sachin", "Java Tech ARC"));

        Student student = studentMap.get(101);
        System.out.println(student.getName());

        hazelcastInstance.shutdown();
    }
}
