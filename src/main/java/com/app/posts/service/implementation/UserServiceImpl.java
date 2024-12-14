package com.app.posts.service.implementation;

import com.app.posts.persistence.entity.UserEntity;
import com.app.posts.service.exception.user.UserNotFoundException;
import com.app.posts.persistence.repository.IUserRepository;
import com.app.posts.service.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        Optional<UserEntity> user = this.userRepository.findById(userId);
        if (user.isEmpty()) throw new UserNotFoundException("User not found");
        return user.get();
    }
}
