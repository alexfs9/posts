package com.app.posts.record.requests.post;

public record UpdatePostRequest(
        Long postId,
        String text
) {
}
