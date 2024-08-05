package com.txdk.chitter.service;

import java.util.List;
import java.util.Set;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.txdk.chitter.entity.Post;
import com.txdk.chitter.entity.User;
import com.txdk.chitter.exception.EntityNotFoundException;
import com.txdk.chitter.repository.PostRepository;
import com.txdk.chitter.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public Post getPost(Long id) {
        Optional<Post> post = postRepository.findById(id);
        return unwrapPost(post, id);
    }

    @Override
    public List<Post> getAllPosts() {
        return (List<Post>) postRepository.findAll();
    }

    @Override
    public Post savePost(Post post, UserDetails userDetails) {
        User user = AuthenticationService.unwrapUser(userRepository.findByUsername(userDetails.getUsername()),
                userDetails.getUsername());
        post.setUser(user);
        return postRepository.save(post);
    }

    @Override
    public Post updatePost(Post post, Long id) {
        Optional<Post> existingPost = postRepository.findById(id);
        Post unwrappedPost = unwrapPost(existingPost, id);
        unwrappedPost.setContent(post.getContent());
        unwrappedPost.setTags(post.getTags());
        return postRepository.save(unwrappedPost);
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public Post likePost(Long id, UserDetails userDetails) {
        User user = AuthenticationService.unwrapUser(userRepository.findByUsername(userDetails.getUsername()),
                userDetails.getUsername());

        Optional<Post> post = postRepository.findById(id);
        Post unwrappedPost = unwrapPost(post, id);
        Set<User> likedUsers = unwrappedPost.getLikedUsers();

        if (likedUsers.contains(user)) likedUsers.remove(user);
        else likedUsers.add(user);

        unwrappedPost.setLikedUsers(likedUsers);

        return postRepository.save(unwrappedPost);
    }

    static Post unwrapPost(Optional<Post> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, Post.class);
    }
    
}
