package com.app.posts.service.interfaces;

import com.app.posts.persistence.entity.PostEntity;
import com.app.posts.presentation.dto.PostDTO;
import com.app.posts.presentation.dto.PostWithoutCommentsDTO;

import java.util.List;

public interface IPostService {

    PostWithoutCommentsDTO save(String text);

    List<PostWithoutCommentsDTO> findAll();

    PostEntity getEntity(Long id);

    PostDTO findById(Long postId);

    PostWithoutCommentsDTO update(PostEntity postEntity, String text);

    void deleteById(Long id);
}
