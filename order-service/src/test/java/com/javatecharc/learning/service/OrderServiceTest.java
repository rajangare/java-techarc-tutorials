package com.javatecharc.learning.service;

import com.javatecharc.learning.model.EmployeeResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderService orderService;

    private MockRestServiceServer mockServer;

    @BeforeEach
    void setUp() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    void testGetEmployeeDetails() {
        Long employeeId = 1L;
        String url = "http://localhost:8090/api/employee/getById/" + employeeId;

        EmployeeResponse mockResponse = new EmployeeResponse();
        mockResponse.setId(employeeId);
        mockResponse.setName("John Doe");
        mockResponse.setDepartment("Engineering");

        mockServer.expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header("Authorization", "Basic " + Base64.getEncoder().encodeToString("admin:admin".getBytes())))
                .andRespond(withSuccess("{\"id\":1,\"name\":\"John Doe\",\"department\":\"Engineering\"}", MediaType.APPLICATION_JSON));

        EmployeeResponse response = orderService.getEmployeeDetails(employeeId);

        assertEquals(mockResponse.getId(), response.getId());
        assertEquals(mockResponse.getName(), response.getName());
        assertEquals(mockResponse.getDepartment(), response.getDepartment());

        mockServer.verify();
    }
}