package com.app.posts.presentation.dto.request.post;

import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public record UpdatePostRequest(
        @NotBlank Long postId,
        @NotBlank String text
) {
}
