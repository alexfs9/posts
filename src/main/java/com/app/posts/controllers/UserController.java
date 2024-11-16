package com.app.posts.controllers;

import com.app.posts.entities.UserEntity;
import com.app.posts.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserEntity save(@RequestBody UserEntity user) {
        return this.userService.save(user);
    }

    @GetMapping("/{userId}")
    public UserEntity findById(@PathVariable Long userId) {
        return this.userService.findById(userId);
    }
}
