package com.app.posts.presentation.dto;

import com.app.posts.presentation.dto.response.UserResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PostWithoutCommentsDTO {

    private Long id;

    @NotBlank(message = "Post is required")
    @Size(max = 255, message = "Post must be less than 255 characters")
    private String text;

    @NotNull(message = "Post creation date is required")
    private LocalDateTime postedAt;

    private LocalDateTime updatedAt;

    private UserResponse user;
}
