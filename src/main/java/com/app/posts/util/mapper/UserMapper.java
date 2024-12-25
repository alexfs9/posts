package com.app.posts.util.mapper;

import com.app.posts.persistence.entity.UserEntity;
import com.app.posts.presentation.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {PostMapper.class, RoleMapper.class})
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    UserEntity userDTOToUserEntity(UserDTO userDTO);

    UserDTO userEntityToUserDTO(UserEntity userEntity);
}
