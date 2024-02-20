package com.app.demo.controllers;

import com.app.demo.dto.AuthDto;
import com.app.demo.dto.LoginDto;
import com.app.demo.dto.RegisterDto;
import com.app.demo.services.abstracts.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AccountService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        return authService.register(registerDto);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthDto> login(@RequestBody LoginDto loginDto) {
        return authService.login(loginDto);
    }
}
