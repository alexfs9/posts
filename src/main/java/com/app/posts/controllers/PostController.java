package com.app.posts.controllers;

import com.app.posts.entities.PostEntity;
import com.app.posts.entities.UserEntity;
import com.app.posts.records.requests.post.NewPostRequest;
import com.app.posts.records.requests.post.UpdatePostRequest;
import com.app.posts.services.PostService;
import com.app.posts.services.UserService;
import lombok.RequiredArgsConstructor;
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
    public PostEntity save(@RequestBody NewPostRequest newPostRequest) {
        UserEntity user = this.userService.findById(newPostRequest.userId());
        return this.postService.save(user, newPostRequest.text());
    }

    @GetMapping("/{postId}")
    public PostEntity findById(@PathVariable Long postId) {
        return this.postService.findById(postId);
    }

    @PatchMapping
    public PostEntity update(@RequestBody UpdatePostRequest updatePostRequest) {
        PostEntity post = this.postService.findById(updatePostRequest.postId());
        return this.postService.update(post, updatePostRequest.text());
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deleteById(@PathVariable Long postId) {
        PostEntity post = this.postService.findById(postId);
        this.postService.deleteById(post.getId());
        return ResponseEntity.noContent().build();
    }
}
