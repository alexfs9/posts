package com.app.posts.util.mapper;

import com.app.posts.persistence.entity.UserEntity;
import com.app.posts.presentation.dto.UserDTO;
import com.app.posts.presentation.dto.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDto(UserEntity userEntity);

    default UserResponse toResponse(UserEntity userEntity) {
        if (userEntity == null) return null;
        return new UserResponse(userEntity.getUsername());
    }
}
