package com.app.posts.service.implementation;

import com.app.posts.persistence.entity.PostEntity;
import com.app.posts.persistence.entity.UserEntity;
import com.app.posts.presentation.dto.PostDTO;
import com.app.posts.presentation.dto.PostWithoutCommentsDTO;
import com.app.posts.service.exception.post.PostNotFoundException;
import com.app.posts.persistence.repository.IPostRepository;
import com.app.posts.service.interfaces.IPostService;
import com.app.posts.util.mapper.PostMapper;
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
    private final PostMapper postMapper;

    @Override
    public PostWithoutCommentsDTO save(String text) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserEntity userEntity = this.userService.getEntity(username);

        PostEntity postEntity = PostEntity.builder()
                .text(text)
                .postedAt(LocalDateTime.now())
                .updatedAt(null)
                .user(userEntity)
                .build();
        postEntity = this.postRepository.save(postEntity);
        return this.postMapper.toDtoWithoutComments(postEntity);
    }

    @Override
    public List<PostWithoutCommentsDTO> findAll() {
        return this.postRepository.findAll()
                .stream()
                .map(this.postMapper::toDtoWithoutComments)
                .toList();
    }

    @Override
    public PostEntity getEntity(Long postId) {
        return this.postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found"));
    }

    @Override
    public PostDTO findById(Long postId) {
        return this.postMapper.toDto(this.getEntity(postId));
    }

    @Override
    public PostWithoutCommentsDTO update(PostEntity postEntity, String text) {
        postEntity.setText(text);
        postEntity.setUpdatedAt(LocalDateTime.now());
        postEntity = this.postRepository.save(postEntity);
        return this.postMapper.toDtoWithoutComments(postEntity);
    }

    @Override
    public void deleteById(Long postId) {
        this.postRepository.deleteById(this.getEntity(postId).getId());
    }
}
