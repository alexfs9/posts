package com.app.posts.persistence.repository;

import com.app.posts.persistence.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostRepository extends JpaRepository<PostEntity, Long> {
}
