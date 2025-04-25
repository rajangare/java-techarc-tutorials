package com.javatecharc.learning.config;

import com.javatecharc.learning.demo.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public Student student() {
        return new Student();
    }

    /*@Bean
    public Student student1() {
        return new Student();
    }*/

    /*@Bean
    public Couse couse() {
        return new Couse(student());
    }*/
}


