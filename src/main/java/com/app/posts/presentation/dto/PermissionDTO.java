package com.app.posts.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PermissionDTO {

    private Long id;

    @NotBlank(message = "Permission name is required")
    private String name;
}
