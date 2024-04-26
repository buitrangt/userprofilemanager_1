package aibles.userprofilemanager_1.service.serviceImpl;

import aibles.userprofilemanager_1.dto.CommentRequest;
import aibles.userprofilemanager_1.dto.CommentResponse;
import aibles.userprofilemanager_1.entity.CommentEntity;
import aibles.userprofilemanager_1.repository.CommentRepository;
import aibles.userprofilemanager_1.service.mapping.CommentMapping;
import aibles.userprofilemanager_1.service.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentMapping commentMapping;

    @Override
    @Transactional
    public CommentResponse createComment(CommentRequest request) {
        CommentEntity comment = commentMapping.convertDtoToEntity(request);
        comment = commentRepository.save(comment);
        return commentMapping.convertEntityToDto(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentResponse> getAllCommentsByPostId(Long postId) {
        List<CommentEntity> comments = commentRepository.findAllByPostId(String.valueOf(postId));
        return comments.stream()
                .map(commentMapping::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteComment(Long commentId) {
        if (!commentRepository.existsById(String.valueOf(commentId))) {
            throw new RuntimeException("Comment not found with id: " + commentId);
        }
        commentRepository.deleteById(String.valueOf(commentId));
    }
}
