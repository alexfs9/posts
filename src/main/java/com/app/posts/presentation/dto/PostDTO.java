package com.app.posts.presentation.dto;

import com.app.posts.presentation.dto.response.CommentResponse;
import com.app.posts.presentation.dto.response.UserResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostDTO {

    private Long id;

    @NotBlank(message = "Post is required")
    @Size(max = 255, message = "Post must be less than 255 characters")
    private String text;

    @NotNull(message = "Post creation date is required")
    private LocalDateTime postedAt;

    private LocalDateTime updatedAt;

    private UserResponse user;

    private List<CommentResponse> comments;
}
