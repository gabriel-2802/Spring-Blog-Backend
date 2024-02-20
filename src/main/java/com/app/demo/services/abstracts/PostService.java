package com.app.demo.services.abstracts;

import com.app.demo.dto.PostDto;
import com.app.demo.entities.Post;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {
    ResponseEntity<String> createPost(PostDto postDto);
    ResponseEntity<String> deletePost(int id);
    ResponseEntity<String> updatePost(int id, PostDto postDto);
    ResponseEntity<List<Post>> allPosts();
    ResponseEntity<Post> findPost(int id);
}
