package com.app.posts.service.interfaces;

import com.app.posts.persistence.entity.UserEntity;

public interface IUserService {

    UserEntity save(UserEntity user);

    UserEntity findById(Long userId);

    UserEntity findUserEntityByUsername(String username);
}
