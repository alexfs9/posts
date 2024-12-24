package com.app.posts.persistence.repository;

import com.app.posts.persistence.entity.RoleEntity;
import com.app.posts.persistence.entity.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {

    Set<RoleEntity> findRoleEntitiesByRoleEnumIn(Set<RoleEnum> roleNames);
}
