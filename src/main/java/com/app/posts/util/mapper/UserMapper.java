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

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "isEnabled", ignore = true)
    @Mapping(target = "accountNonExpired", ignore = true)
    @Mapping(target = "credentialsNonExpired", ignore = true)
    @Mapping(target = "accountNonLocked", ignore = true)
    @Mapping(target = "roles", ignore = true)
    UserDTO userEntityToUserDTO(UserEntity userEntity);
}
