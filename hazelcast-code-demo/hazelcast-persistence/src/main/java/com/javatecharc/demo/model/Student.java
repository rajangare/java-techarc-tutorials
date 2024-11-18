package com.javatecharc.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public class Student {
    private int studentId;
    private String studentName;

    private int departmentId;
    private List<Course> courses;

    //create setter and getter or use lombok lib
}
