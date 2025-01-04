package com.app.posts.util.mapper;

import com.app.posts.persistence.entity.CommentEntity;
import com.app.posts.presentation.dto.CommentDTO;
import com.app.posts.presentation.dto.response.CommentResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface CommentMapper {

    CommentDTO toDto(CommentEntity commentEntity);

    default CommentResponse toResponse(CommentEntity commentEntity) {
        if (commentEntity == null) return null;
        return new CommentResponse(
                UserMapper.INSTANCE.toResponse(commentEntity.getUser()),
                commentEntity.getText(),
                commentEntity.getCommentedAt(),
                commentEntity.getUpdatedAt()
        );
    }
}
