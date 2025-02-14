package com.app.posts.service.implementation;

import com.app.posts.persistence.entity.*;
import com.app.posts.persistence.repository.IPostRepository;
import com.app.posts.presentation.dto.PostWithoutCommentsDTO;
import com.app.posts.presentation.dto.response.UserResponse;
import com.app.posts.util.mapper.PostMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {

    @Mock
    private IPostRepository postRepository;

    @Mock
    private UserServiceImpl userService;

    @Mock
    private PostMapper postMapper;

    @InjectMocks
    private PostServiceImpl postService;

    private Authentication authentication;

    private UserEntity userEntity;

    private final String username = "testerPro";

    private PostEntity postEntity;

    private UserResponse userResponse;

    private PostWithoutCommentsDTO postWithoutCommentsDTO;

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

        this.postEntity = PostEntity.builder()
                .text("This is a test post")
                .postedAt(LocalDateTime.now())
                .updatedAt(null)
                .user(this.userEntity)
                .build();

        this.userResponse = new UserResponse(this.userEntity.getUsername());

        this.postWithoutCommentsDTO = PostWithoutCommentsDTO.builder()
                .text(this.postEntity.getText())
                .postedAt(this.postEntity.getPostedAt())
                .updatedAt(this.postEntity.getUpdatedAt())
                .user(this.userResponse)
                .build();

        this.authentication = new UsernamePasswordAuthenticationToken(this.username, null);
        SecurityContextHolder.getContext().setAuthentication(this.authentication);
    }

    @Test
    void testSave() {
        when(this.userService.getEntity(anyString())).thenReturn(this.userEntity);
        when(this.postRepository.save(any(PostEntity.class))).thenReturn(this.postEntity);
        when(this.postMapper.toDtoWithoutComments(this.postEntity)).thenReturn(this.postWithoutCommentsDTO);

        PostWithoutCommentsDTO savedPost = this.postService.save(this.postEntity.getText());

        assertThat(savedPost).isNotNull();
        assertThat(savedPost).isEqualTo(this.postWithoutCommentsDTO);
        verify(this.postRepository, times(1)).save(any(PostEntity.class));
    }
}
