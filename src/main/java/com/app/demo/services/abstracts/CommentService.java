package com.app.demo.services.abstracts;

import com.app.demo.dto.CommentDto;
import org.springframework.http.ResponseEntity;

public interface CommentService {
    ResponseEntity<String> createComment(CommentDto commentDto, int postId, String username);

    ResponseEntity<String> deleteComment(int commentId);

    ResponseEntity<String> updateComment(int commentId, CommentDto commentDto);
}
