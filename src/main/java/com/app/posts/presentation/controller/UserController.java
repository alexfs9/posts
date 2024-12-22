package com.app.posts.presentation.controller;

import com.app.posts.persistence.entity.UserEntity;
import com.app.posts.service.implementation.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@PreAuthorize("denyAll()")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<UserEntity> save(@RequestBody UserEntity user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.save(user));
    }

    @GetMapping("/{userId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<UserEntity> findById(@PathVariable Long userId) {
        return ResponseEntity.ok(this.userService.findById(userId));
    }
}
