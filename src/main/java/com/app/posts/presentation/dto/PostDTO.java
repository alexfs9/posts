package com.app.posts.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDTO {

    private Long id;

    @NotBlank(message = "Text is required")
    @Size(max = 255, message = "Text must be less than 255 characters")
    private String text;

    @NotBlank(message = "Post creation date is required")
    private LocalDateTime postedAt;

    private LocalDateTime updatedAt;

    @NotBlank(message = "User is required")
    private UserDTO user;
}
