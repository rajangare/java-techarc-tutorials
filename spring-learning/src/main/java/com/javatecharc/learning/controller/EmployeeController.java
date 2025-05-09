package com.javatecharc.learning.controller;

import com.javatecharc.learning.model.EmployeeRequest;
import com.javatecharc.learning.model.EmployeeResponse;
import com.javatecharc.learning.service.EmployeeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
@SecurityRequirement(name = "Authorization")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createEmployee(@RequestBody(required = true) EmployeeRequest request) {
        String employeeMessage = employeeService.registerEmployee(request);
        return new ResponseEntity<>(employeeMessage, HttpStatus.CREATED);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<EmployeeResponse> getEmployee(@PathVariable("id") Long id) {
        EmployeeResponse employeeResponse = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<EmployeeResponse> getEmployeeByName(@PathVariable("name") String name) {
        EmployeeResponse employeeResponse = employeeService.getEmployeeByName(name);
        return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
    }
}
