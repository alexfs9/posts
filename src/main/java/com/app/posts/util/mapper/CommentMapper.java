package com.app.posts.util.mapper;

import com.app.posts.persistence.entity.CommentEntity;
import com.app.posts.presentation.dto.CommentDTO;
import com.app.posts.presentation.dto.response.CommentResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface CommentMapper {

    CommentDTO toDto(CommentEntity commentEntity);

    CommentResponse toResponse(CommentEntity commentEntity);
}
