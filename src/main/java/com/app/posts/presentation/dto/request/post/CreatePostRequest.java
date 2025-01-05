package com.app.posts.presentation.dto.request.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreatePostRequest(
        @NotBlank @Size(max = 50, message = "Post must be less than 255 characters") String text
) {
}
