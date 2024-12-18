package com.app.posts.presentation.dto.requests.post;

public record NewPostRequest(
        Long userId,
        String text
) {
}
