package com.app.posts.presentation.dto.request.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MakeCommentRequest(
        @NotNull Long postId,
        @NotBlank @Size(max = 255, message = "Comments cannot have more than 255 characters") String text
) {
}
