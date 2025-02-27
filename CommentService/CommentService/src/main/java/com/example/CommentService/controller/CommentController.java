package com.example.CommentService.controller;

import com.example.CommentService.entity.Comment;
import com.example.CommentService.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/{postId}/user/{userId}")
    public ResponseEntity<String> addComment(@PathVariable Long postId,
                                             @PathVariable String userId,
                                             @RequestBody String content) {
        String result = commentService.addComment(postId, userId, content);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<String> updateComment(@PathVariable Long commentId,
                                                @RequestBody String content) {
        String result = commentService.updateComment(commentId, content);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId) {
        String result = commentService.deleteComment(commentId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<Comment>> getCommentsByPostId(@PathVariable Long postId) {
        List<Comment> comments = commentService.getCommentsByPostId(postId);
        return ResponseEntity.ok(comments);
    }
}
