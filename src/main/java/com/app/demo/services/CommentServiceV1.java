package com.app.demo.services;

import com.app.demo.dto.CommentDto;
import com.app.demo.entities.Comment;
import com.app.demo.entities.Post;
import com.app.demo.entities.User;
import com.app.demo.repositories.CommentRepository;
import com.app.demo.repositories.PostRepository;
import com.app.demo.repositories.UserRepository;
import com.app.demo.services.abstracts.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class CommentServiceV1 implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    @Override
    public ResponseEntity<String> createComment(CommentDto commentDto, int postId, String username) {
        if (!postRepository.existsById(postId)) {
            return ResponseEntity.badRequest().body("Post not found");
        }

        if (!userRepository.existsByUsername(username)) {
            return ResponseEntity.badRequest().body("User not found");
        }

        Comment comment = new Comment();
        createComment(comment, commentDto, postId, username);
        commentRepository.save(comment);

        Post post = postRepository.findById(postId).get();
        post.getComments().add(comment);
        postRepository.save(post);

        User user = userRepository.findByUsername(username).get();
        user.getComments().add(comment);
        userRepository.save(user);

        return ResponseEntity.ok("Comment created");
    }

    private void createComment(Comment comment, CommentDto commentDto, int postId, String username) {
        comment.setPost(postRepository.findById(postId).get());
        comment.setAuthor(userRepository.findByUsername(username).get());
        comment.setComment(commentDto.getComment());
        comment.setDate(new Date());
    }

    @Override
    public ResponseEntity<String> deleteComment(int commentId) {
        if (!commentRepository.existsById(commentId)) {
            return ResponseEntity.badRequest().body("Comment not found");
        }

        Comment comment = commentRepository.findById(commentId).get();

        User user = comment.getAuthor();
        user.getComments().remove(comment);

        Post post = comment.getPost();
        post.getComments().remove(comment);

        commentRepository.deleteById(commentId);
        return ResponseEntity.ok("Comment deleted");
    }

    @Override
    public ResponseEntity<String> updateComment(int commentId, CommentDto commentDto) {
        if (!commentRepository.existsById(commentId)) {
            return ResponseEntity.badRequest().body("Comment not found");
        }
        Comment comment = commentRepository.findById(commentId).get();
        comment.setComment(commentDto.getComment());
        comment.setDate(new Date());
        commentRepository.save(comment);
        return ResponseEntity.ok("Comment updated");
    }
}
