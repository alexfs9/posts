package com.app.posts.service.interfaces;

import com.app.posts.persistence.entity.PostEntity;

import java.util.List;

public interface IPostService {

    PostEntity save(String text);

    List<PostEntity> findAll();

    PostEntity update(PostEntity post, String text);

    void deleteById(Long id);
}
