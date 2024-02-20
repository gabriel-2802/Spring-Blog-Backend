package com.app.demo.repositories;

import com.app.demo.entities.Comment;
import com.app.demo.entities.Post;
import com.app.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    void deleteAllByAuthor(User user);
    void deleteAllByPost(Post post);
}
