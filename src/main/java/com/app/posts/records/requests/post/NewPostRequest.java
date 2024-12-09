package com.app.posts.records.requests.post;

public record NewPostRequest(
        Long userId,
        String text
) {
}
