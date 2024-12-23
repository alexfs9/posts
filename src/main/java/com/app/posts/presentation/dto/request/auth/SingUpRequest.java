package com.app.posts.presentation.dto.request.auth;

import com.app.posts.presentation.dto.request.auth.role.CreateRoleRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public record SingUpRequest(
        @NotBlank String username,
        @NotBlank String email,
        @NotBlank String password,
        @Valid CreateRoleRequest roleRequest
) {
}
