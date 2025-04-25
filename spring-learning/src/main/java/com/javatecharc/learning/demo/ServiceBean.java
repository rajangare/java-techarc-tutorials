package com.javatecharc.learning.demo;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

public class ServiceBean {
    public Student studentBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("src/main/resources/spring.xml");
        Student student = (Student) context.getBean("student");
        System.out.println(student);

        return student;
    }
}
