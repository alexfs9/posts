package com.app.posts.service.implementation;

import com.app.posts.persistence.entity.PostEntity;
import com.app.posts.persistence.entity.UserEntity;
import com.app.posts.service.exception.post.PostNotFoundException;
import com.app.posts.persistence.repository.IPostRepository;
import com.app.posts.service.interfaces.IPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements IPostService {

    private final IPostRepository postRepository;

    @Override
    public PostEntity save(UserEntity user, String text) {
        PostEntity post = new PostEntity();
        post.setText(text);
        post.setPostedAt(LocalDateTime.now());
        post.setUpdatedAt(null);
        post.setUser(user);
        return this.postRepository.save(post);
    }

    @Override
    public List<PostEntity> findAll() {
        return this.postRepository.findAll();
    }

    public PostEntity findById(long postId) {
        Optional<PostEntity> post = this.postRepository.findById(postId);
        if (post.isEmpty()) throw new PostNotFoundException("Post not found");
        return post.get();
    }

    @Override
    public PostEntity update(PostEntity post, String text) {
        post.setText(text);
        post.setUpdatedAt(LocalDateTime.now());
        return this.postRepository.save(post);
    }

    @Override
    public void deleteById(Long id) {
        this.postRepository.deleteById(id);
    }
}
