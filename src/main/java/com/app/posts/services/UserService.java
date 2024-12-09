package com.app.posts.services;

import com.app.posts.entities.UserEntity;
import com.app.posts.exceptions.user.UserNotFoundException;
import com.app.posts.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity save(UserEntity user) {
        return this.userRepository.save(user);
    }

    public UserEntity findById(Long userId) {
        Optional<UserEntity> user = this.userRepository.findById(userId);
        if (user.isEmpty()) throw new UserNotFoundException("User not found");
        return user.get();
    }
}
