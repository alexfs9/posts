package com.app.posts.presentation.record.responses;

import java.time.LocalDateTime;

public record ErrorResponse(
        LocalDateTime time,
        String message,
        int status
) {
}
