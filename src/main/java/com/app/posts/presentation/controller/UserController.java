package com.app.posts.presentation.controller;

import com.app.posts.persistence.entity.UserEntity;
import com.app.posts.service.implementation.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping
    public ResponseEntity<UserEntity> save(@RequestBody UserEntity user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.save(user));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserEntity> findById(@PathVariable Long userId) {
        return ResponseEntity.ok(this.userService.findById(userId));
    }
}