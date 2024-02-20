package com.app.demo.services;

import com.app.demo.dto.AuthDto;
import com.app.demo.dto.LoginDto;
import com.app.demo.dto.RegisterDto;
import com.app.demo.entities.Role;
import com.app.demo.entities.User;
import com.app.demo.repositories.CommentRepository;
import com.app.demo.repositories.RoleRepository;
import com.app.demo.repositories.UserRepository;
import com.app.demo.Constants;
import com.app.demo.security.JwtGenerator;
import com.app.demo.services.abstracts.AccountService;
import com.app.demo.services.abstracts.CommentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static java.awt.AWTEventMulticaster.add;
import com.app.demo.entities.Comment;

@Service
@RequiredArgsConstructor
public class AccountServiceV1 implements AccountService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtGenerator jwtGenerator;
    private final RoleRepository roleRepository;
    private final CommentService commentService;
    @Override
    public ResponseEntity<String> register(RegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(registerDto.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }

        User newUser = new User();
        register(newUser, registerDto);
        userRepository.save(newUser);
        return ResponseEntity.ok("User registered successfully!");

    }

    private void register(User user, RegisterDto registerDto) {
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setProfilePicture(registerDto.getProfilePicture());
        Role roles = roleRepository.findByAuthority(Constants.USER).get();
        user.setRoles(new HashSet<Role>() {{
            add(roles);
        }});
    }

    @Override
    public ResponseEntity<AuthDto> login(LoginDto loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                            loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtGenerator.generateToken(authentication);
            return ResponseEntity.ok(new AuthDto(token));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body(new AuthDto("Invalid credentials!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new AuthDto("Error: " + e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<String> update(RegisterDto updateDto, String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            return ResponseEntity.badRequest().body("Error: User not found!");
        }

        update(user, updateDto);

        userRepository.save(user);
        return ResponseEntity.ok("User " + username + " updated successfully!");
    }

    private void update(User user, RegisterDto updateDto) {
        if (updateDto.getUsername() != null) {
            user.setUsername(updateDto.getUsername());
        }
        if (updateDto.getEmail() != null) {
            user.setEmail(updateDto.getEmail());
        }
        if (updateDto.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(updateDto.getPassword()));
        }
        if (updateDto.getProfilePicture() != null) {
            user.setProfilePicture(updateDto.getProfilePicture());
        }
    }

    @Override
    @Transactional
    public ResponseEntity<String> delete(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            return ResponseEntity.badRequest().body("Error: User not found!");
        }

        List<Integer> commentIds = user.getComments().stream()
                .map(Comment::getId)
                .toList();
        commentIds.forEach(commentService::deleteComment);

        userRepository.delete(user);
        return ResponseEntity.ok("User " + username + " deleted successfully!");
    }

    @Override
    public ResponseEntity<User> info(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            return ResponseEntity.badRequest().body(null);
        }

        return ResponseEntity.ok(user);
    }
}
