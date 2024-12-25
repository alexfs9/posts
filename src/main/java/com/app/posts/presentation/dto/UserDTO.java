package com.app.posts.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserDTO {

    private Long id;

    @NotBlank(message = "Username is required")
    @Size(max = 50, message = "Username must be less than 50 characters")
    private String username;

    @NotBlank(message = "Email is required")
    @Size(max = 200, message = "Email must be less than 200 characters")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    private List<PostDTO> posts;

    @NotBlank(message = "Enabled is required")
    private boolean isEnabled;

    @NotBlank(message = "Account non expired is required")
    private boolean accountNonExpired;

    @NotBlank(message = "Credentials non expired is required")
    private boolean credentialsNonExpired;

    @NotBlank(message = "Account non locked is required")
    private boolean accountNonLocked;

    @NotBlank(message = "Roles are required")
    private List<RoleDTO> roles;
}
