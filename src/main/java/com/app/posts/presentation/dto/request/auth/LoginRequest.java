package com.app.posts.presentation.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public record LoginRequest(
        @NotBlank String username,
        @NotBlank String password
) {
}
