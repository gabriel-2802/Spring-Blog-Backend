package com.app.demo.services.abstracts;

import com.app.demo.dto.AuthDto;
import com.app.demo.dto.LoginDto;
import com.app.demo.dto.RegisterDto;
import com.app.demo.entities.User;
import org.springframework.http.ResponseEntity;

public interface AccountService {
    ResponseEntity<String> register(RegisterDto registerDto);

    ResponseEntity<AuthDto> login(LoginDto loginDto);

    ResponseEntity<String> update(RegisterDto updateDto, String username);

    ResponseEntity<String> delete(String username);

    ResponseEntity<User> info(String username);
}
