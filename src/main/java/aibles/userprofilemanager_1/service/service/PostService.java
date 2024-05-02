package aibles.userprofilemanager_1.service.service;

import aibles.userprofilemanager_1.dto.request.ImageRequest;
import aibles.userprofilemanager_1.dto.request.PostRequest;
import aibles.userprofilemanager_1.dto.response.ImageResponse;
import aibles.userprofilemanager_1.dto.response.PostResponse;

import java.util.List;

public interface PostService {
    PostResponse create(PostRequest request);
    List<PostResponse> getAllPosts();
    PostResponse getPostById(Long id);
    PostResponse updatePosts(Long id, PostRequest request);
    void deletePost(Long id);
    PostResponse sharePost(String userProfileId, String postId, PostRequest request, String author);
    ImageResponse uploadImage(String postId, ImageRequest request);

}
