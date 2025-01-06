package com.app.posts.service.implementation;

import com.app.posts.persistence.entity.CommentEntity;
import com.app.posts.persistence.entity.PostEntity;
import com.app.posts.persistence.entity.UserEntity;
import com.app.posts.persistence.repository.ICommentRepository;
import com.app.posts.presentation.dto.CommentDTO;
import com.app.posts.presentation.dto.request.comment.MakeCommentRequest;
import com.app.posts.service.interfaces.ICommentService;
import com.app.posts.util.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements ICommentService {

    private final PostServiceImpl postService;
    private final UserServiceImpl userService;
    private final ICommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Override
    public CommentDTO save(MakeCommentRequest makeCommentRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PostEntity postEntity = this.postService.getEntity(makeCommentRequest.postId());
        UserEntity userEntity = this.userService.getEntity(authentication.getName());
        CommentEntity commentEntity = CommentEntity.builder()
                .post(postEntity)
                .user(userEntity)
                .text(makeCommentRequest.text())
                .commentedAt(LocalDateTime.now())
                .updatedAt(null)
                .build();
        commentEntity = this.commentRepository.save(commentEntity);
        return this.commentMapper.toDto(commentEntity);
    }
}
