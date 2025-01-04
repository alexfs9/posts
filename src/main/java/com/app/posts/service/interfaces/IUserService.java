package com.app.posts.service.interfaces;

import com.app.posts.persistence.entity.UserEntity;
import com.app.posts.presentation.dto.UserDTO;

public interface IUserService {

    UserEntity save(UserEntity user);

    UserEntity getUserEntity(String username);

    UserDTO findByUsername(String username);
}
