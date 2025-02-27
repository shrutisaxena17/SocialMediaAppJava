package com.example.CommentService.service;

import com.example.CommentService.entity.Comment;
import com.example.CommentService.repo.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepo commentRepository;

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

    public String addComment(Long postId, String userId, String content) {
        if (!checkPostExists(postId)) {
            return "Post not found.";
        }

        Comment comment = new Comment();
        comment.setPostId(postId);
        comment.setUserId(userId);
        comment.setContent(content);
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUpdatedAt(LocalDateTime.now());

        commentRepository.save(comment);
        return "Comment added successfully!";
    }

    public String updateComment(Long commentId, String content) {
        Optional<Comment> existingComment = commentRepository.findById(commentId);

        if (existingComment.isEmpty()) {
            return "Comment not found.";
        }

        Comment comment = existingComment.get();
        comment.setContent(content);
        comment.setUpdatedAt(LocalDateTime.now());

        commentRepository.save(comment);
        return "Comment updated successfully!";
    }

    public String deleteComment(Long commentId) {
        Optional<Comment> existingComment = commentRepository.findById(commentId);

        if (existingComment.isEmpty()) {
            return "Comment not found.";
        }

        commentRepository.delete(existingComment.get());
        return "Comment deleted successfully!";
    }

    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }
}
