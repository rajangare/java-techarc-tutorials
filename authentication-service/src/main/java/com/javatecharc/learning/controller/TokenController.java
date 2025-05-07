package com.javatecharc.learning.controller;

import com.javatecharc.learning.service.JwtTokenService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/token")
public class TokenController {
    private final JwtTokenService jwtTokenService;

    public TokenController(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    @PostMapping("/generate")
    public String generateToken(@RequestParam String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", "USER");
        return jwtTokenService.generateToken(username, claims);
    }

    @GetMapping("/validate")
    public boolean validateToken(@RequestParam String token) {
        return jwtTokenService.validateToken(token);
    }
}

