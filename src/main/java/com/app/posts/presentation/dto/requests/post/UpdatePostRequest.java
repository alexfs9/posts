package com.app.posts.presentation.dto.requests.post;

public record UpdatePostRequest(
        Long postId,
        String text
) {
}
