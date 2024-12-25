package com.app.posts.util.mapper;

import com.app.posts.persistence.entity.UserEntity;
import com.app.posts.presentation.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = PostMapper.class)
public interface UserMapper {

    UserDTO toDto(UserEntity userEntity);
}
