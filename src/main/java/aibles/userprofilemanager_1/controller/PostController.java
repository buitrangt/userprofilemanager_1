package aibles.userprofilemanager_1.controller;

import aibles.userprofilemanager_1.dto.PostRequest;
import aibles.userprofilemanager_1.dto.PostResponse;
import aibles.userprofilemanager_1.service.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostResponse> createPost(@RequestBody PostRequest request) {
        PostResponse response = postService.create(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        List<PostResponse> responses = postService.getAllPosts();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable Long id) {
        PostResponse response = postService.getPostById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> updatePost(@PathVariable Long id, @RequestBody PostRequest request) {
        PostResponse updatedPost = postService.updatePosts(id, request);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.ok().build();
    }


// Share a post
@PostMapping("/{postId}/share")
public ResponseEntity<PostResponse> sharePost(@PathVariable String postId, @RequestBody PostRequest request, @RequestParam String userProfileId, @RequestParam String author) {
    PostResponse postResponse = postService.sharePost(userProfileId, postId, request, author);
    return new ResponseEntity<>(postResponse, HttpStatus.OK);
}
}
