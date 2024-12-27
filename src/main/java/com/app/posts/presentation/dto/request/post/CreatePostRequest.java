package com.app.posts.presentation.dto.request.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

@Validated
public record CreatePostRequest(
        @NotBlank @Size(max = 50, message = "Post cannot have more than 255 characters") String text
) {
}