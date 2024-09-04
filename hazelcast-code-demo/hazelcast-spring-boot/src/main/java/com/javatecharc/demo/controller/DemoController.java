package com.javatecharc.demo.controller;

import com.javatecharc.demo.service.HazelcastDemoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DemoController {
    public final HazelcastDemoService hazelcastDemoService;

    public DemoController(HazelcastDemoService hazelcastDemoService) {
        this.hazelcastDemoService = hazelcastDemoService;
    }

    @GetMapping("/demo")
    public ResponseEntity<?> demo() {
        return new ResponseEntity<>(hazelcastDemoService.hazelcastDemo(), HttpStatus.OK);
    }

    @GetMapping("/demo")
    public ResponseEntity<?> demoCacheable() {
        return new ResponseEntity<>(hazelcastDemoService.cacheableDemo("demKey"), HttpStatus.OK);
    }
}
