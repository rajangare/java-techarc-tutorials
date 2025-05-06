package com.javatecharc.learning.config;

import com.javatecharc.learning.interceptor.MyLearningInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApplicationConfig implements WebMvcConfigurer {
    @Autowired
    private MyLearningInterceptor myLearningInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myLearningInterceptor)
                .addPathPatterns("/api/**") // Intercept only specific paths
                .excludePathPatterns("/api/employee/getById/*"); // Exclude specific paths
    }

}


