package com.app.posts.presentation.record.requests.post;

public record UpdatePostRequest(
        Long postId,
        String text
) {
}
