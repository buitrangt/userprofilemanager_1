package aibles.userprofilemanager_1.service.service;

import aibles.userprofilemanager_1.dto.PostRequest;
import aibles.userprofilemanager_1.dto.PostResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {
    PostResponse create(PostRequest request);
    List<PostResponse> getAllPosts();
    PostResponse getPostById(Long id);
    PostResponse updatePosts(Long id, PostRequest request);
    void deletePost(Long id);

   // Page<PostResponse> finAllPosts(Pageable pageable, String filter);
}
