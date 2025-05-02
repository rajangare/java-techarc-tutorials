package com.javatecharc.learning.service;

import com.javatecharc.learning.model.EmployeeResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {
    private final RestTemplate restTemplate;

    public OrderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public EmployeeResponse getEmployeeDetails(Long employeeId) {
        String url = "http://localhost:8090/api/employee/getById/"+employeeId; // URL of the Employee Service
        return restTemplate.getForObject(url, EmployeeResponse.class);
    }
}
