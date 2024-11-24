package com.javatecharc.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Department {
    private int id;
    private String departmentName;

    //create setter and getter or use lombok lib
}
