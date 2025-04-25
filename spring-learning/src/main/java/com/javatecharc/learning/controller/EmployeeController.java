package com.javatecharc.learning.controller;

import com.javatecharc.learning.entity.Employee;
import com.javatecharc.learning.model.EmployeeRequest;
import com.javatecharc.learning.model.EmployeeResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @PostMapping("/create")
    public ResponseEntity<String> createEmployee(@RequestBody(required = true) EmployeeRequest request) {
        return new ResponseEntity<>("Employee Created Successfully!", HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<EmployeeResponse> getEmployee(@PathVariable("id") Long id) {
        return new ResponseEntity<EmployeeResponse>(new EmployeeResponse(), HttpStatus.OK);
    }
}
