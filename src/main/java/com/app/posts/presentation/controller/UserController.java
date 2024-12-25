package com.app.posts.presentation.controller;

import com.app.posts.presentation.dto.UserDTO;
import com.app.posts.service.implementation.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@PreAuthorize("denyAll()")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping("/{username}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<UserDTO> findByUsername(@PathVariable String username) {
        return ResponseEntity.ok(this.userService.findByUsername(username));
    }
}
