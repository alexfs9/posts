package com.app.posts.util.mapper;

import com.app.posts.persistence.entity.PostEntity;
import com.app.posts.presentation.dto.PostDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "user", ignore = true)
    PostDTO toDto(PostEntity postEntity);
}
