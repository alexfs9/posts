package com.app.posts.controllers;

import com.app.posts.entity.PostEntity;
import com.app.posts.entity.UserEntity;
import com.app.posts.record.requests.post.NewPostRequest;
import com.app.posts.record.requests.post.UpdatePostRequest;
import com.app.posts.service.PostService;
import com.app.posts.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final UserService userService;

    @GetMapping
    public List<PostEntity> findAll() {
        return this.postService.findAll();
    }

    @PostMapping
    public ResponseEntity<PostEntity> save(@RequestBody NewPostRequest newPostRequest) {
        UserEntity user = this.userService.findById(newPostRequest.userId());
        return ResponseEntity.status(HttpStatus.CREATED).body(this.postService.save(user, newPostRequest.text()));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostEntity> findById(@PathVariable Long postId) {
        return ResponseEntity.ok(this.postService.findById(postId));
    }

    @PatchMapping
    public ResponseEntity<PostEntity> update(@RequestBody UpdatePostRequest updatePostRequest) {
        PostEntity post = this.postService.findById(updatePostRequest.postId());
        return ResponseEntity.ok(this.postService.update(post, updatePostRequest.text()));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deleteById(@PathVariable Long postId) {
        PostEntity post = this.postService.findById(postId);
        this.postService.deleteById(post.getId());
        return ResponseEntity.noContent().build();
    }
}
