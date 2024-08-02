package com.txdk.chitter.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.txdk.chitter.ApplicationConstants;
import com.txdk.chitter.entity.Post;
import com.txdk.chitter.service.PostService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "http://#{'${server.address}'}" + ApplicationConstants.FRONTEND_SERVER_PORT)
@AllArgsConstructor
@RequestMapping(ApplicationConstants.POST_ENDPOINT_PREFIX)
public class PostController {

    private PostService postService;

    final Logger logger = LoggerFactory.getLogger(PostController.class);

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable Long id) {
        logger.info("GET request received on endpoint {}/{}", ApplicationConstants.POST_ENDPOINT_PREFIX, id);
        return new ResponseEntity<>(postService.getPost(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Post>> getPosts() {
        logger.info("GET request received on endpoint {}/all", ApplicationConstants.POST_ENDPOINT_PREFIX);
        return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Post> savePost(@AuthenticationPrincipal UserDetails userDetails, @Valid @RequestBody Post post) {
        logger.info("POST request received on endpoint {} from user: {}", ApplicationConstants.POST_ENDPOINT_PREFIX, userDetails.getUsername());
        return new ResponseEntity<>(postService.savePost(post, userDetails), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@Valid @RequestBody Post post, @PathVariable Long id) {
        logger.info("PUT request received on endpoint {}/{}", ApplicationConstants.POST_ENDPOINT_PREFIX, id);
        return new ResponseEntity<>(postService.updatePost(post, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePost(@PathVariable Long id) {
        logger.info("DELETE request received on endpoint {}/{}", ApplicationConstants.POST_ENDPOINT_PREFIX, id);
        postService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
