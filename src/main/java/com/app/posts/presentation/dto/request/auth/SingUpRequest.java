package com.app.posts.presentation.dto.request.auth;

import com.app.posts.presentation.dto.request.auth.role.CreateRoleRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SingUpRequest(
        @NotBlank @Size(max = 50, message = "Username cannot have more than 50 characters") String username,
        @NotBlank @Size(max = 200, message = "Email cannot have more than 200 characters") String email,
        @NotBlank String password,
        @Valid CreateRoleRequest roleRequest
) {
}
