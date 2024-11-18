package com.javatecharc.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Course {
    private int courseId;
    private String courseName;

    //create setter and getter or use lombok lib
}
