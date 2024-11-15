package com.app.posts.services;

import com.app.posts.entities.Post;
import com.app.posts.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post save(Post post) {
        post.setPostedAt(LocalDateTime.now());
        post.setUpdatedAt(null);
        return this.postRepository.save(post);
    }

    public List<Post> findAll() {
        return this.postRepository.findAll();
    }

    public Optional<Post> findById(long id) {
        return this.postRepository.findById(id);
    }

    public Post update(Post oldPost, Post newPost) {
        newPost.setPostedAt(oldPost.getPostedAt());
        newPost.setUpdatedAt(LocalDateTime.now());
        return this.postRepository.save(newPost);
    }

    public void deleteById(long id) {
        this.postRepository.deleteById(id);
    }
}
