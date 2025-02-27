package com.example.LikeMicroService.service;

import com.example.LikeMicroService.entity.Like;
import com.example.LikeMicroService.repo.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static final String POST_SERVICE_URL = "http://localhost:8082/posts/";

    private boolean checkPostExists(Long postId) {
        String url = POST_SERVICE_URL + postId;
        try {
            restTemplate.getForEntity(url, String.class);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String likePost(Long postId, String userId) {
        if (!checkPostExists(postId)) {
            return "Post not found.";
        }

        Optional<Like> existingLike = likeRepository.findByPostIdAndUserId(postId, userId);

        if (existingLike.isPresent()) {
            return "Already liked.";
        }

        Like like = new Like();
        like.setPostId(postId);
        like.setUserId(userId);
        likeRepository.save(like);

        return "Post liked successfully!";
    }

    public String unlikePost(Long postId, String userId) {
        Optional<Like> existingLike = likeRepository.findByPostIdAndUserId(postId, userId);

        if (existingLike.isEmpty()) {
            return "Like not found.";
        }

        likeRepository.delete(existingLike.get());
        return "Post unliked successfully!";
    }

    public List<Like> getLikesByPostId(Long postId) {
        return likeRepository.findByPostId(postId);
    }
}

