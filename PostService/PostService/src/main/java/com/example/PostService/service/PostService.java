package com.example.PostService.service;

import com.example.PostService.entity.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    Post createPost(Post post);
    List<Post> getPostsByUserId(Long userId);
    List<Post> getAllPosts();
    Optional<Post> getPostById(Long id);
    boolean deletePost(Long id);
}
