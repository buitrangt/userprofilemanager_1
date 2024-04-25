package aibles.userprofilemanager_1.service.service;

import aibles.userprofilemanager_1.dto.CommentRequest;
import aibles.userprofilemanager_1.dto.CommentResponse;

import java.util.List;

public interface CommentService {
    CommentResponse createComment(CommentRequest request);
    List<CommentResponse> getAllCommentsByPostId(Long postId);
    void deleteComment(Long commentId);
}
