package com.app.posts.presentation.record.requests.post;

public record NewPostRequest(
        Long userId,
        String text
) {
}
