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
    public String generateJWTToken(@RequestHeader String username, @RequestHeader String password) {

        return jwtTokenService.generateToken(username, password);
    }

    @GetMapping("/validate")
    public boolean validateToken(@RequestParam String jwtToken) {
        return jwtTokenService.validateToken(jwtToken);
    }
}

