package com.app.posts.records.requests.post;

public record UpdatePostRequest(
        Long postId,
        String text
) {
}
