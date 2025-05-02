package com.javatecharc.learning.controller;

import com.javatecharc.learning.model.EmployeeResponse;
import com.javatecharc.learning.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    // Add your order-related endpoints here
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/get/emp/details")
    public ResponseEntity<EmployeeResponse> getEmployeeDetails(@RequestParam Long employeeId) {
        EmployeeResponse employeeResponse =  orderService.getEmployeeDetails(employeeId);
        System.out.println(employeeResponse);
        return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
    }
}
