package com.app.posts.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostWithoutUserDTO {

    private Long id;

    @NotBlank(message = "Post is required")
    @Size(max = 255, message = "Post must be less than 255 characters")
    private String text;

    @NotNull(message = "Post creation date is required")
    private LocalDateTime postedAt;

    private LocalDateTime updatedAt;
}
