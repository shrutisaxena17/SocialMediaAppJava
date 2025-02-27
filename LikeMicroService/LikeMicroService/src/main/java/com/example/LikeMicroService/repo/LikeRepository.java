package com.example.LikeMicroService.repo;

import com.example.LikeMicroService.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like,Long> {
    Optional<Like> findByPostIdAndUserId(Long postId, String userId);
    List<Like> findByPostId(Long postId);
}
