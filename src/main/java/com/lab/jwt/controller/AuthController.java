package com.lab.jwt.controller;

import com.lab.jwt.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/token")
    public ResponseEntity<Map<String, String>> generateToken(@RequestBody LoginRequest request) {
        // Lab demo only: accepts any non-empty username/password. Real apps must verify credentials.
        if (request.username() == null || request.username().isBlank()
                || request.password() == null || request.password().isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Username and password are required"));
        }
        String token = jwtService.generateToken(request.username());
        return ResponseEntity.ok(Map.of("token", token, "tokenType", "Bearer"));
    }

    @PostMapping("/validate")
    public ResponseEntity<Map<String, Object>> validateToken(@RequestBody TokenRequest request) {
        boolean valid = request.token() != null && jwtService.isValid(request.token());
        return ResponseEntity.ok(Map.of("valid", valid));
    }

    public record LoginRequest(String username, String password) {}
    public record TokenRequest(String token) {}
}
