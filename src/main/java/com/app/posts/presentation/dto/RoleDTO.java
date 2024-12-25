package com.app.posts.presentation.dto;

import com.app.posts.persistence.entity.RoleEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class RoleDTO {

    private Long id;

    @NotBlank(message = "Role name is required")
    private RoleEnum roleEnum;

    @NotBlank(message = "Permissions are required")
    private Set<PermissionDTO> permissions;
}
