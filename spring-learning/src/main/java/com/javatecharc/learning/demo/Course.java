package com.javatecharc.learning.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Course {

    @Autowired
   private Student studentDemo;

    /*// Constructor injection
    public Course(Student student) {
        this.student = student;
    }*/

    /*public String getStudentName() {
        // Using the student bean
        student.registerStudent();
        return student.getName();
    }*/

    /*// Setter injection
    @Autowired
    public void setStudent(Student studentABc) {
        this.student = studentABc;
    }*/
}
