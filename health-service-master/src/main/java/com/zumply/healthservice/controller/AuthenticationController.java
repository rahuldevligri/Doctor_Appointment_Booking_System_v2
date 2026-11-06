package com.zumply.healthservice.controller;


import com.zumply.healthservice.jwt.JwtTokenProvider;
import com.zumply.healthservice.request.AuthenticationRequest;
import com.zumply.healthservice.entity.User;
import com.zumply.healthservice.repository.UserRepository;
import com.zumply.healthservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserRepository users;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody AuthenticationRequest data) {

        try {
            String username = data.getUsername();
            Integer id = users.findByUsername(username).get().getId();
            var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            String token = jwtTokenProvider.createToken(authentication);
            Map<String, Object> model = new HashMap<>();
            model.put("id", id);
            model.put("username", username);
            model.put("token", token);
            return ResponseEntity.ok(model);

        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body("Invalid username/password supplied");
        }
    }
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid AuthenticationRequest data) {
        if (this.users.findByUsername(data.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("User  already exists");
        }
        else {
            userService.saveUser(data);
            return ResponseEntity.ok("User registered successfully");
        }
}

@GetMapping("/users/{id}")
public ResponseEntity<?> getUser(@PathVariable Integer id) {
    return ok(users.findById(id));}
    
}
