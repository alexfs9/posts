package com.app.posts.service.implementation;

import com.app.posts.persistence.entity.PostEntity;
import com.app.posts.persistence.entity.UserEntity;
import com.app.posts.persistence.repository.IPostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {

    @Mock
    private IPostRepository postRepository;

    @InjectMocks
    private PostServiceImpl postService;

    private List<PostEntity> posts;

    private List<UserEntity> users;

    @BeforeEach
    void setUp() {
        UserEntity userOne = new UserEntity();
        userOne.setId(1L);
        userOne.setUsername("username");
        userOne.setEmail("user@gmail.com");
        userOne.setPassword("password");

        UserEntity userTwo = new UserEntity();
        userTwo.setId(2L);
        userTwo.setUsername("newUsername");
        userTwo.setEmail("new_user@gmail.com");
        userTwo.setPassword("new_password");

        PostEntity postOne = new PostEntity();
        postOne.setId(1L);
        postOne.setText("first post");
        postOne.setUser(userOne);
        postOne.setPostedAt(LocalDateTime.now());
        postOne.setUpdatedAt(null);

        PostEntity postTwo = new PostEntity();
        postTwo.setId(2L);
        postTwo.setText("second post");
        postTwo.setUser(userTwo);
        postTwo.setPostedAt(LocalDateTime.now());
        postTwo.setUpdatedAt(null);

        this.posts = List.of(postOne, postTwo);
        this.users = List.of(userOne, userTwo);
    }

    @Test
    void testSave() {
        UserEntity user = this.users.getFirst();
        PostEntity post = this.posts.getFirst();
        String text = "first post";

        when(this.postRepository.save(any(PostEntity.class))).thenReturn(post);

        PostEntity savedPost = this.postService.save(user, text);

        assertNotNull(savedPost);
        assertInstanceOf(Long.class, savedPost.getId());
        assertEquals(text, savedPost.getText());
        assertEquals(post.getPostedAt(), savedPost.getPostedAt());
        assertEquals(user, savedPost.getUser());
        assertNull(savedPost.getUpdatedAt());
    }

    @Test
    void testFindAll() {
        when(this.postRepository.findAll()).thenReturn(this.posts);

        List<PostEntity> foundPosts = this.postService.findAll();

        assertNotNull(foundPosts);
        assertEquals(2, foundPosts.size());
        assertEquals(this.posts.getFirst(), foundPosts.getFirst());
        assertEquals(this.posts.get(1), foundPosts.get(1));
    }
}
