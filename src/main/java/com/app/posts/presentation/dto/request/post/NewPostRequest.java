package com.app.posts.presentation.dto.request.post;

public record NewPostRequest(
        Long userId,
        String text
) {
}
