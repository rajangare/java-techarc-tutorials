package com.javatecharc.demo.jet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hazelcast.config.IndexType;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.HazelcastJsonValue;
import com.hazelcast.map.IMap;
import com.hazelcast.query.PagingPredicate;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.Predicates;
import com.javatecharc.demo.model.Course;
import com.javatecharc.demo.model.Department;
import com.javatecharc.demo.model.Student;

import java.util.Collection;
import java.util.List;

public class HazelcastPagingJoinExample {

    static HazelcastInstance hazelcastInstance;
    static IMap<Integer, HazelcastJsonValue> studentMap;
    static IMap<Integer, HazelcastJsonValue> courseMap;
    static IMap<Integer, HazelcastJsonValue> departmentMap;

    static Gson gson = new GsonBuilder().create();

    public static void main(String[] args) {
        // Initialize Hazelcast instance
         hazelcastInstance = Hazelcast.newHazelcastInstance();

        // Define maps
        studentMap = hazelcastInstance.getMap("studentMap");
        departmentMap = hazelcastInstance.getMap("departmentMap");
        courseMap = hazelcastInstance.getMap("courseMap");

        //create index for a column
        studentMap.addIndex(IndexType.SORTED, "departmentId");

        //create sample data for demo
        createSampleData();

        //create sample predicates
        PagingPredicate<Integer, HazelcastJsonValue> pagingPredicate = Predicates.pagingPredicate(3);
        Collection<HazelcastJsonValue> searchedResult = studentMap.values(pagingPredicate);
        System.out.println("searchedResult : "+searchedResult);

        //traverse to the next page
        pagingPredicate.nextPage();
        searchedResult = studentMap.values(pagingPredicate);
        System.out.println("Next page - searchedResult : "+searchedResult);

        //Search array attribute
        Predicate<Object, Object> predicates = Predicates.equal("courses[any].courseId", "501");
        PagingPredicate<Integer, HazelcastJsonValue> arraySearch = Predicates.pagingPredicate(predicates,3);
        searchedResult = studentMap.values(arraySearch);
        System.out.println("Array Predicate - searchedResult : "+searchedResult);


        //Join two maps
        hazelcastInstance.shutdown();
    }

    private static void createSampleData() {
        Course course1 = new Course(501, "Physics");
        Course course2 = new Course(502, "Chemistry");
        Course course3 = new Course(501, "Maths");
        courseMap.put(501, new HazelcastJsonValue(gson.toJson(course1)));
        courseMap.put(502, new HazelcastJsonValue(gson.toJson(course2)));
        courseMap.put(503, new HazelcastJsonValue(gson.toJson(course3)));

        Student student1 = new Student(101, "Sachin", 1001, List.of(course1, course3));
        Student student2 = new Student(102, "Rahul", 1002, List.of(course2));
        Student student3 = new Student(103, "Rachel", 1001, List.of(course3));
        Student student4 = new Student(104, "Villa", 1001, List.of(course1));
        Student student5 = new Student(105, "Nev am", 1002, List.of(course2));
        studentMap.put(101, new HazelcastJsonValue(gson.toJson(student1)));
        studentMap.put(103, new HazelcastJsonValue(gson.toJson(student3)));
        studentMap.put(102, new HazelcastJsonValue(gson.toJson(student2)));
        studentMap.put(105, new HazelcastJsonValue(gson.toJson(student5)));
        studentMap.put(104, new HazelcastJsonValue(gson.toJson(student4)));

        Department department1 = new Department(1001, "Physics Department");
        Department department2 = new Department(1002, "Chemistry Department");
        departmentMap.put(1001, new HazelcastJsonValue(gson.toJson(department1)));
        departmentMap.put(1002, new HazelcastJsonValue(gson.toJson(department2)));

        System.out.println(gson.toJson(course3));
        System.out.println(studentMap.values());
        System.out.println(departmentMap.values());
        System.out.println(courseMap.values());
    }
}
