package com.app.demo.controllers;

import com.app.demo.dto.RegisterDto;
import com.app.demo.entities.User;
import com.app.demo.services.abstracts.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    public final AccountService accountService;

    @PostMapping("/update/{username}")
    public ResponseEntity<String> update(@RequestBody RegisterDto updateDto, @PathVariable String username){
        return accountService.update(updateDto, username);

    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<String> delete(@PathVariable String username) {
        return accountService.delete(username);
    }

    @GetMapping("/info/{username}")
    public ResponseEntity<User> info(@PathVariable String username) {
        return accountService.info(username);
    }

}
