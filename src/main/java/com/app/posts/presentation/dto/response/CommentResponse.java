package com.app.posts.presentation.dto.response;

import java.time.LocalDateTime;

public record CommentResponse(
        UserResponse user,
        String text,
        LocalDateTime commentedAt,
        LocalDateTime updatedAt
) {
}
