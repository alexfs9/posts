package com.app.posts.presentation.dto.request.post;

public record UpdatePostRequest(
        Long postId,
        String text
) {
}
