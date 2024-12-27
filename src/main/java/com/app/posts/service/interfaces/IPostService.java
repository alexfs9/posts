package com.app.posts.service.interfaces;

import com.app.posts.presentation.dto.PostDTO;
import com.app.posts.presentation.dto.request.post.UpdatePostRequest;

import java.util.List;

public interface IPostService {

    PostDTO save(String text);

    List<PostDTO> findAll();

    PostDTO findById(Long postId);

    PostDTO update(UpdatePostRequest updatePostRequest);

    void deleteById(Long id);
}
