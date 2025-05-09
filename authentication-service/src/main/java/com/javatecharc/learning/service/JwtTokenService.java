package com.javatecharc.learning.service;

import com.javatecharc.learning.exception.UserNotFoundException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtTokenService {

    @Value("${credential.username}")
    private String username;

    @Value("${credential.password}")
    private String password;

    @Value("${secretKey}")
    private String secretKey;

    @Value("${expirationTime}")
    private long expirationTime; // 1hour in milliseconds

    // Generate a JWT token
    public String generateToken(String username, String password) {

        //TODO: Validate username and cred with database
        // For demonstration, we are just encoding the credentials
        if(!username.equals(this.username) || !password.equals(this.password)) {
            throw new UserNotFoundException("Invalid credentials, please try again.");
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", "USER");

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSignInKey(), SignatureAlgorithm.HS384)
                .compact();
    }

    // Validate a JWT token
    public boolean validateToken(String token) {
        try {
            System.out.println();
            Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            System.out.println("Invalid JWT token: " + e.getMessage());
            return false;
        }
    }

    // Extract claims from a JWT token
    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = secretKey.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
