package com.app.posts.controllers.advice;

import com.app.posts.exceptions.post.PostNotFoundException;
import com.app.posts.records.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class PostAdvice {

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePostNotFoundException(PostNotFoundException ex) {
        return new ResponseEntity<>(
                new ErrorResponse(
                        LocalDateTime.now(),
                        ex.getMessage(),
                        HttpStatus.NOT_FOUND.value()
                ),
                HttpStatus.NOT_FOUND);
    }
}
