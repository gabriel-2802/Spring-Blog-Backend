package com.app.demo.controllers;

import com.app.demo.dto.PostDto;
import com.app.demo.entities.Post;
import com.app.demo.services.abstracts.PostService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<String> createPost(@RequestBody PostDto postDto) {
        return postService.createPost(postDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Post>> allPosts() {
        return postService.allPosts();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Post> findPost(@PathVariable int id) {
        return postService.findPost(id);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<String> updatePost(@PathVariable int id, @RequestBody PostDto postDto) {
        return postService.updatePost(id, postDto);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<String> deletePost(@PathVariable int id) {
        return postService.deletePost(id);
    }

}
