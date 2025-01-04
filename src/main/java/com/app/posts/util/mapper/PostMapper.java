package com.app.posts.util.mapper;

import com.app.posts.persistence.entity.PostEntity;
import com.app.posts.presentation.dto.PostDTO;
import com.app.posts.presentation.dto.PostWithoutCommentsDTO;
import com.app.posts.presentation.dto.PostWithoutUserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class, CommentMapper.class})
public interface PostMapper {

    PostWithoutUserDTO toDtoWithoutUser(PostEntity postEntity);

    PostWithoutCommentsDTO toDtoWithoutComments(PostEntity postEntity);

    PostDTO toDto(PostEntity postEntity);
}
