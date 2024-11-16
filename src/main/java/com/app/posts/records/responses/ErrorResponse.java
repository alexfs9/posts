package com.app.posts.records.responses;

import java.time.LocalDateTime;

public record ErrorResponse(
        LocalDateTime time,
        String message,
        int status
) {
}
