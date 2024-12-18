package com.app.posts.presentation.dto.responses;

import java.time.LocalDateTime;

public record ErrorResponse(
        LocalDateTime time,
        String message,
        int status
) {
}
