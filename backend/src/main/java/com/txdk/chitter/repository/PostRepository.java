package com.txdk.chitter.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.txdk.chitter.entity.Post;

public interface PostRepository extends CrudRepository<Post, Long> {

    List<Post> findByUserId(Long userId);
    
}
