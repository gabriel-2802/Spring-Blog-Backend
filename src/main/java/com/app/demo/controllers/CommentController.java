package com.app.demo.controllers;

import com.app.demo.dto.CommentDto;
import com.app.demo.services.abstracts.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/create/{postId}&&{username}")
    public ResponseEntity<String> createComment(@RequestBody CommentDto commentDto, @PathVariable int postId, @PathVariable String username) {
        return commentService.createComment(commentDto, postId, username);
    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable int commentId) {
        return commentService.deleteComment(commentId);
    }

    @PutMapping("/update/{commentId}")
    public ResponseEntity<String> updateComment(@PathVariable int commentId, @RequestBody CommentDto commentDto) {
        return commentService.updateComment(commentId, commentDto);
    }


}
