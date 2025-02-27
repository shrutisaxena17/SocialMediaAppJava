package com.example.LikeMicroService.controller;

import com.example.LikeMicroService.entity.Like;
import com.example.LikeMicroService.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/likes")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @PostMapping("/{postId}/user/{userId}")
    public ResponseEntity<String> likePost(@PathVariable Long postId, @PathVariable String userId) {
        String result = likeService.likePost(postId, userId);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{postId}/user/{userId}")
    public ResponseEntity<String> unlikePost(@PathVariable Long postId, @PathVariable String userId) {
        String result = likeService.unlikePost(postId, userId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<Like>> getLikesByPostId(@PathVariable Long postId) {
        List<Like> likes = likeService.getLikesByPostId(postId);
        return ResponseEntity.ok(likes);
    }
}
