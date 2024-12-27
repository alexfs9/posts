package com.app.posts.presentation.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

@Validated
public record LoginRequest(
        @NotBlank @Size(max = 50, message = "Username cannot have more than 50 characters") String username,
        @NotBlank String password
) {
}
