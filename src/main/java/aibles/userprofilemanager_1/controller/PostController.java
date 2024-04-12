package aibles.userprofilemanager_1.controller;


import aibles.userprofilemanager_1.dto.PostRequest;
import aibles.userprofilemanager_1.dto.PostResponse;
import aibles.userprofilemanager_1.service.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class PostController {
    private final PostService postService;

    @Autowired
    private PostController(PostService postService)
    {
        this.postService=postService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponse craetePost(@RequestBody PostRequest request)
    {
        return postService.create(request);
    }

    @GetMapping
    public List<PostResponse> getAllStudents() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public PostResponse getStudentById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostResponse updateStudent(@PathVariable Long id, @RequestBody PostRequest request) {
        return postService.updatePosts(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable Long id) {
        postService.deletePost(id);
    }
}
