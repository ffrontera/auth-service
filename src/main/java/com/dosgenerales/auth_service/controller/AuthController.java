package com.dosgenerales.auth_service.controller;

import com.dosgenerales.auth_service.dto.AuthResponseDTO;
import com.dosgenerales.auth_service.dto.LoginRequestDTO;
import com.dosgenerales.auth_service.dto.RegisterRequestDTO;
import com.dosgenerales.auth_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterRequestDTO request) {
            return ResponseEntity.ok(authService.register(request));
    }
}
