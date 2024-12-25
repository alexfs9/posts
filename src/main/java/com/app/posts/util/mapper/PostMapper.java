package com.app.posts.util.mapper;

import com.app.posts.persistence.entity.PostEntity;
import com.app.posts.presentation.dto.PostDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    PostDTO toDto(PostEntity postEntity);
}
