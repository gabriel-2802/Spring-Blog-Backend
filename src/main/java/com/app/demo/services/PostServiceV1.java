package com.app.demo.services;

import com.app.demo.dto.PostDto;
import com.app.demo.entities.Category;
import com.app.demo.entities.Comment;
import com.app.demo.entities.Post;
import com.app.demo.repositories.PostRepository;
import com.app.demo.services.abstracts.CommentService;
import com.app.demo.services.abstracts.PostService;
import com.app.demo.entities.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceV1 implements PostService {
    private final PostRepository postRepository;
    private final CommentService commentService;
    @Override
    public ResponseEntity<String> createPost(PostDto postDto) {
        if (postDto == null) {
            return ResponseEntity.badRequest().body("Post cannot be empty");
        }
        Post post = new Post();
        createPost(post, postDto);
        postRepository.save(post);

        return ResponseEntity.ok("Post created successfully");
    }

    private void createPost(Post post, PostDto postDto) {
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDate(new Date());
        post.setAuthor(postDto.getAuthor());
        post.setCategory(Category.valueOf(postDto.getCategory()));
        post.setImage(postDto.getImage());
    }

    @Override
    public ResponseEntity<String> deletePost(int id) {
        if (postRepository.existsById(id)) {
            Post post = postRepository.findById(id).get();
            List<Integer> commentIds = post.getComments().stream()
                    .map(Comment::getId)
                    .toList();
            commentIds.forEach(commentService::deleteComment);

            postRepository.deleteById(id);
            return ResponseEntity.ok("Post deleted successfully");
        }
        return ResponseEntity.badRequest().body("Post not found");
    }

    @Override
    public ResponseEntity<String> updatePost(int id, PostDto postDto) {
        Post post = postRepository.findById(id).orElse(null);
        if (post == null) {
            return ResponseEntity.badRequest().body("Post not found");
        }

        updatePost(post, postDto);
        postRepository.save(post);
        return ResponseEntity.ok("Post updated successfully");
    }

    private void updatePost(Post post, PostDto postDto) {
        if (postDto.getTitle() != null) {
            post.setTitle(postDto.getTitle());
        }
        if (postDto.getContent() != null) {
            post.setContent(postDto.getContent());
        }
        if (postDto.getImage() != null) {
            post.setImage(postDto.getImage());
        }

        if (postDto.getAuthor() != null) {
            post.setAuthor(postDto.getAuthor());
        }

        if (postDto.getCategory() != null) {
            post.setCategory(Category.valueOf(postDto.getCategory()));
        }

        post.setDate(new Date());
    }

    @Override
    public ResponseEntity<List<Post>> allPosts() {
        return ResponseEntity.ok(postRepository.findAll());
    }

    @Override
    public ResponseEntity<Post> findPost(int id) {
        Post post = postRepository.findById(id).orElse(null);
        if (post == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(post);
    }

    @Override
    public ResponseEntity<List<String>> categories() {
        return ResponseEntity.ok(Category.getCategories());
    }

    @Override
    public ResponseEntity<List<Post>> category(String category) {
        return ResponseEntity.ok(postRepository.findByCategory(Category.valueOf(category)));
    }
}
