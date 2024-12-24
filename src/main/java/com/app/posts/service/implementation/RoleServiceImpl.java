package com.app.posts.service.implementation;

import com.app.posts.persistence.entity.RoleEntity;
import com.app.posts.persistence.entity.RoleEnum;
import com.app.posts.persistence.repository.IRoleRepository;
import com.app.posts.service.interfaces.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {

    private final IRoleRepository roleRepository;

    @Override
    public Set<RoleEntity> findRoleEntitiesByRoleEnumIn(List<String> roleNames) {
        Set<RoleEnum> validRoles = roleNames.stream()
                .map(roleName -> {
                    try {
                        return RoleEnum.valueOf(roleName);
                    } catch (IllegalArgumentException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        if (validRoles.isEmpty()) throw new IllegalArgumentException("Given roles not found");

        return this.roleRepository.findRoleEntitiesByRoleEnumIn(validRoles);
    }
}
