package com.app.posts.presentation.controller;

import com.app.posts.presentation.dto.request.auth.LoginRequest;
import com.app.posts.presentation.dto.request.auth.SingUpRequest;
import com.app.posts.presentation.dto.response.AuthResponse;
import com.app.posts.service.implementation.UserDetailsServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@PreAuthorize("permitAll()")
@RequiredArgsConstructor
public class AuthController {

    private final UserDetailsServiceImpl userDetailsService;

    @PostMapping("/log-in")
    public ResponseEntity<AuthResponse> logIn(@RequestBody @Valid LoginRequest loginRequest) {
        return ResponseEntity.ok(this.userDetailsService.logIn(loginRequest));
    }

    @PostMapping("/sing-up")
    public ResponseEntity<AuthResponse> singUp(@RequestBody @Valid SingUpRequest singUpRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userDetailsService.singUp(singUpRequest));
    }
}
