package com.app.posts.util.mapper;

import com.app.posts.persistence.entity.PostEntity;
import com.app.posts.presentation.dto.PostDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UserMapper.class})
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(target = "id", ignore = true)
    PostEntity postDTOToPostEntity(PostDTO postDTO);

    PostDTO postEntityToPostDTO(PostEntity postEntity);
}
