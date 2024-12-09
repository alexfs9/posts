package com.app.posts.record.responses;

import java.time.LocalDateTime;

public record ErrorResponse(
        LocalDateTime time,
        String message,
        int status
) {
}
