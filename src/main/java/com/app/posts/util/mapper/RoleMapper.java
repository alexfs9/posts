package com.app.posts.util.mapper;

import com.app.posts.persistence.entity.RoleEntity;
import com.app.posts.presentation.dto.RoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {PermissionMapper.class})
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    @Mapping(target = "id", ignore = true)
    RoleEntity roleDTOToRoleEntity(RoleDTO roleDTO);

    RoleDTO roleEntityToRoleDTO(RoleEntity roleEntity);
}
