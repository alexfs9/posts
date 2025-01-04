package com.app.posts.presentation.controller;

import com.app.posts.presentation.dto.CommentDTO;
import com.app.posts.presentation.dto.request.comment.MakeCommentRequest;
import com.app.posts.service.implementation.CommentServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentServiceImpl commentService;

    @PostMapping()
    @PreAuthorize("hasAuthority('MAKE_COMMENT')")
    public ResponseEntity<CommentDTO> save(@RequestBody @Valid MakeCommentRequest makeCommentRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.commentService.save(makeCommentRequest));
    }
}
