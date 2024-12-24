package com.app.posts.service.implementation;

import com.app.posts.persistence.entity.UserEntity;
import com.app.posts.service.exception.user.UserNotFoundException;
import com.app.posts.persistence.repository.IUserRepository;
import com.app.posts.service.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;

    @Override
    public UserEntity save(UserEntity user) {
        return this.userRepository.save(user);
    }

    @Override
    public UserEntity findById(Long userId) {
        return this.userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public UserEntity findUserEntityByUsername(String username) {
        return this.userRepository.findUserEntityByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }
}
