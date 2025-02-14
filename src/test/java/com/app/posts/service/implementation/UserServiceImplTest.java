package com.app.posts.service.implementation;

import com.app.posts.persistence.entity.PermissionEntity;
import com.app.posts.persistence.entity.RoleEntity;
import com.app.posts.persistence.entity.RoleEnum;
import com.app.posts.persistence.entity.UserEntity;
import com.app.posts.persistence.repository.IUserRepository;
import com.app.posts.presentation.dto.UserDTO;
import com.app.posts.service.exception.user.UserNotFoundException;
import com.app.posts.util.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    private UserEntity userEntity;

    private UserDTO userDTO;

    private final String username = "testerPro";

    @BeforeEach
    void setup() {
        PermissionEntity createPostPermission = PermissionEntity.builder()
                .name("CREATE_POST")
                .build();
        PermissionEntity updatePostPermission = PermissionEntity.builder()
                .name("UPDATE_POST")
                .build();
        PermissionEntity deletePostPermission = PermissionEntity.builder()
                .name("DELETE_POST")
                .build();
        PermissionEntity makeCommentPermission = PermissionEntity.builder()
                .name("MAKE_COMMENT")
                .build();

        RoleEntity basicUserRole = RoleEntity.builder()
                .roleEnum(RoleEnum.BASIC_USER)
                .permissions(Set.of(createPostPermission, updatePostPermission, deletePostPermission, makeCommentPermission))
                .build();

        this.userEntity = UserEntity.builder()
                .username(this.username)
                .email("tester_pro123@gmail.com")
                .password("t#st9812_")
                .isEnabled(true)
                .accountNonExpired(true)
                .credentialsNonExpired(true)
                .accountNonLocked(true)
                .roles(Set.of(basicUserRole))
                .build();

        this.userDTO = UserDTO.builder()
                .username(this.username)
                .email("tester_pro123@gmail.com")
                .build();
    }

    @Test
    void testSave() {
        when(this.userRepository.save(any(UserEntity.class))).thenReturn(this.userEntity);

        UserEntity savedUser = this.userService.save(this.userEntity);

        assertThat(savedUser).isNotNull();
        assertThat(savedUser).isEqualTo(this.userEntity);
        assertThat(savedUser.getUsername()).isEqualTo(this.userDTO.getUsername());
        assertThat(savedUser.getEmail()).isEqualTo(this.userDTO.getEmail());
        verify(this.userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    void testGetEntity() {
        when(this.userRepository.findByUsername(anyString())).thenReturn(Optional.of(this.userEntity));

        UserEntity foundUser = this.userService.getEntity(this.username);

        assertThat(foundUser).isNotNull();
        assertThat(foundUser).isEqualTo(this.userEntity);
    }

    @Test
    void testGetEntityNotFound() {
        when(this.userRepository.findByUsername(anyString())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> this.userService.getEntity("noUsername"))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage("User not found");
    }

    @Test
    void testFindByUsername() {
        when(this.userRepository.findByUsername(anyString())).thenReturn(Optional.of(this.userEntity));
        when(this.userMapper.toDto(any(UserEntity.class))).thenReturn(this.userDTO);

        UserDTO foundUser = this.userService.findByUsername(this.username);

        assertThat(foundUser).isNotNull();
        assertThat(foundUser).isEqualTo(this.userDTO);
    }
}
