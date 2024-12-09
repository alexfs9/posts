package com.app.posts.record.requests.post;

public record NewPostRequest(
        Long userId,
        String text
) {
}
