package com.app.posts.util.mapper;

import com.app.posts.persistence.entity.PermissionEntity;
import com.app.posts.presentation.dto.PermissionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PermissionMapper {

    PermissionMapper INSTANCE = Mappers.getMapper(PermissionMapper.class);

    @Mapping(target = "id", ignore = true)
    PermissionEntity permissionDTOToPermissionEntity(PermissionDTO permissionDTO);

    PermissionDTO permissionEntityToPermissionDTO(PermissionEntity permissionEntity);
}
