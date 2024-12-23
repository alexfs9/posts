package com.app.posts.presentation.dto.request.auth.role;

import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public record CreateRoleRequest(
        @Size(max = 1, message = "Basic users cannot have more than 1 role") List<String> roleNames
) {
}
