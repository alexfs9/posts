package com.app.posts.service.implementation;

import com.app.posts.persistence.entity.PostEntity;
import com.app.posts.persistence.entity.UserEntity;
import com.app.posts.service.exception.post.PostNotFoundException;
import com.app.posts.persistence.repository.IPostRepository;
import com.app.posts.service.interfaces.IPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements IPostService {

    private final IPostRepository postRepository;
    private final UserServiceImpl userService;

    @Override
    public PostEntity save(String text) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserEntity userEntity = this.userService.findByUsernameToService(username);

        PostEntity post = PostEntity.builder()
                .text(text)
                .postedAt(LocalDateTime.now())
                .updatedAt(null)
                .user(userEntity)
                .build();
        return this.postRepository.save(post);
    }

    @Override
    public List<PostEntity> findAll() {
        return this.postRepository.findAll();
    }

    public PostEntity findById(long postId) {
        return this.postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found"));
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
