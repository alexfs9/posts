package com.app.posts.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDateTime;

@JsonPropertyOrder({"time", "message", "status"})
public record ErrorResponse(
        LocalDateTime time,
        String message,
        int status
) {
}
