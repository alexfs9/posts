package com.app.posts.service;

import com.app.posts.entity.PostEntity;
import com.app.posts.entity.UserEntity;
import com.app.posts.exception.post.PostNotFoundException;
import com.app.posts.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    public PostEntity save(UserEntity user, String text) {
        PostEntity post = new PostEntity();
        post.setText(text);
        post.setPostedAt(LocalDateTime.now());
        post.setUpdatedAt(null);
        post.setUser(user);
        return this.postRepository.save(post);
    }

    public List<PostEntity> findAll() {
        return this.postRepository.findAll();
    }

    public PostEntity findById(long postId) {
        Optional<PostEntity> post = this.postRepository.findById(postId);
        if (post.isEmpty()) throw new PostNotFoundException("Post not found");
        return post.get();
    }

    public PostEntity update(PostEntity post, String text) {
        post.setText(text);
        post.setUpdatedAt(LocalDateTime.now());
        return this.postRepository.save(post);
    }

    public void deleteById(long id) {
        this.postRepository.deleteById(id);
    }
}
