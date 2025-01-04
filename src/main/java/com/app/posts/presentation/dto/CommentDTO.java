package com.app.posts.presentation.dto;

import com.app.posts.presentation.dto.response.UserResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDTO {

    private Long id;

    @NotNull(message = "Post is required")
    private PostWithoutCommentsDTO post;

    @NotNull(message = "User is required")
    private UserResponse user;

    @NotBlank(message = "Comment is required")
    @Size(max = 255, message = "Comment must be less than 255 characters")
    private String text;

    @NotNull(message = "Comment creation date is required")
    private LocalDateTime commentedAt;

    private LocalDateTime updatedAt;
}
