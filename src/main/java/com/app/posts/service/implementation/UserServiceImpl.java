package com.app.posts.service.implementation;

import com.app.posts.persistence.entity.UserEntity;
import com.app.posts.presentation.dto.UserDTO;
import com.app.posts.service.exception.user.UserNotFoundException;
import com.app.posts.persistence.repository.IUserRepository;
import com.app.posts.service.interfaces.IUserService;
import com.app.posts.util.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserEntity save(UserEntity user) {
        return this.userRepository.save(user);
    }

    @Override
    public UserDTO findByUsername(String username) {
        Optional<UserEntity> userEntity = this.userRepository.findByUsername(username);
        if (userEntity.isEmpty()) throw new UserNotFoundException("User not found");
        return this.userMapper.toDto(userEntity.get());
    }

    @Override
    public UserEntity findByUsernameToService(String username) {
        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }
}
