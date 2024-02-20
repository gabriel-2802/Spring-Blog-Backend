package com.app.demo.repositories;

import com.app.demo.entities.Category;
import com.app.demo.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByCategory(Category category);
}
