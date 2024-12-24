package com.app.posts.service.interfaces;

import com.app.posts.persistence.entity.RoleEntity;

import java.util.List;
import java.util.Set;

public interface IRoleService {

    Set<RoleEntity> findRoleEntitiesByRoleEnumIn(List<String> roleNames);
}
