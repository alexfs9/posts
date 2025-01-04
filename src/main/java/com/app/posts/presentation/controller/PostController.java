package com.app.posts.presentation.controller;

import com.app.posts.persistence.entity.PostEntity;
import com.app.posts.presentation.dto.PostDTO;
import com.app.posts.presentation.dto.PostWithoutCommentsDTO;
import com.app.posts.presentation.dto.request.post.CreatePostRequest;
import com.app.posts.presentation.dto.request.post.UpdatePostRequest;
import com.app.posts.service.implementation.PostServiceImpl;
import com.app.posts.service.implementation.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@PreAuthorize("denyAll()")
@RequiredArgsConstructor
public class PostController {

    private final PostServiceImpl postService;
    private final UserServiceImpl userService;

    @GetMapping
    @PreAuthorize("permitAll()")
    public List<PostWithoutCommentsDTO> findAll() {
        return this.postService.findAll();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_POST')")
    public ResponseEntity<PostWithoutCommentsDTO> save(@RequestBody @Valid CreatePostRequest createPostRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.postService.save(createPostRequest.text()));
    }

    @GetMapping("/{postId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<PostDTO> findById(@PathVariable Long postId) {
        return ResponseEntity.ok(this.postService.findById(postId));
    }

    @PatchMapping
    @PreAuthorize("hasAuthority('UPDATE_POST')")
    public ResponseEntity<PostWithoutCommentsDTO> update(@RequestBody @Valid UpdatePostRequest updatePostRequest) {
        PostEntity postEntity = this.postService.getEntity(updatePostRequest.postId());
        return ResponseEntity.ok(this.postService.update(postEntity, updatePostRequest.text()));
    }

    @DeleteMapping("/{postId}")
    @PreAuthorize("hasAuthority('DELETE_POST')")
    public ResponseEntity<Void> deleteById(@PathVariable Long postId) {
        this.postService.deleteById(postId);
        return ResponseEntity.noContent().build();
    }
}
