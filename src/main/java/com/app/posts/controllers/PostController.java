package com.app.posts.controllers;

import com.app.posts.entities.Post;
import com.app.posts.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<Post> findAll() {
        return this.postService.findAll();
    }

    @PostMapping
    public Post save(@RequestBody Post post) {
        return this.postService.save(post);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable Long id) {
        Optional<Post> post = this.postService.findById(id);
        return post.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping
    public ResponseEntity<Post> update(@RequestBody Post newPost) {
        Optional<Post> oldPost = this.postService.findById(newPost.getId());
        if (oldPost.isPresent()) {
            Post postUpdated = this.postService.update(oldPost.get(), newPost);
            return ResponseEntity.ok().body(postUpdated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        Optional<Post> post = this.postService.findById(id);
        if (post.isPresent()) {
            this.postService.deleteById(post.get().getId());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
