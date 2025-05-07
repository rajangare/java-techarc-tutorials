package com.javatecharc.learning.service;

import com.javatecharc.learning.model.EmployeeResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@Service
public class OrderService {
    private final RestTemplate restTemplate;

    public OrderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public EmployeeResponse getEmployeeDetails(Long employeeId) {
        String url = "http://localhost:8090/api/employee/getById/"+employeeId; // URL of the Employee Service

        String authStr = "admin:admin";
        String base64Creds = Base64.getEncoder().encodeToString(authStr.getBytes());
        System.out.println("Base64 Encoded Credentials: " + base64Creds);

        // create headers
        HttpHeaders headers = new HttpHeaders();
        // create request
        HttpEntity<String> request = new HttpEntity<String>(headers);

        headers.add("Authorization", "Basic " + base64Creds); //Bearers token

        ResponseEntity<EmployeeResponse> response = restTemplate.exchange(url, HttpMethod.GET, request, EmployeeResponse.class);

        return response.getBody();
    }
}
