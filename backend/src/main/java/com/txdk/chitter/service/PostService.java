package com.txdk.chitter.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.txdk.chitter.entity.Post;

public interface PostService {
    Post getPost(Long id);
    List<Post> getAllPosts();
    Post savePost(Post post, UserDetails userDetails);
    Post updatePost(Post post, Long id);
    void deletePost(Long id);
}
