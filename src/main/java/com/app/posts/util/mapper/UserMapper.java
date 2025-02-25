package com.app.posts.util.mapper;

import com.app.posts.persistence.entity.UserEntity;
import com.app.posts.presentation.dto.UserDTO;
import com.app.posts.presentation.dto.response.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDto(UserEntity userEntity);

    UserResponse toResponse(UserEntity userEntity);
}
