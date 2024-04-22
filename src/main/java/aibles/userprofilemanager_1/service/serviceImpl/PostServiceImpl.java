package aibles.userprofilemanager_1.service.serviceImpl;

import aibles.userprofilemanager_1.dto.PostRequest;
import aibles.userprofilemanager_1.dto.PostResponse;
import aibles.userprofilemanager_1.entity.PostEntity;
import aibles.userprofilemanager_1.exception.NotFoundException;
import aibles.userprofilemanager_1.repository.PostRepository;
import aibles.userprofilemanager_1.service.mapping.PostMapping;
import aibles.userprofilemanager_1.service.service.PostService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    @Transactional
    public PostResponse create(PostRequest request) {
        PostEntity entity = PostMapping.convertDtoEntity(request);
        PostEntity savedEntity = postRepository.save(entity);
        return PostMapping.convertEntityToPostResponse(savedEntity);
    }

    @Override
    @Transactional
    public List<PostResponse> getAllPosts() {
        List<PostEntity> entities = postRepository.findAll();
        return entities.stream().map(PostMapping::convertEntityToPostResponse).collect(Collectors.toList());
    }

    @Override
    public PostResponse getPostById(Long id) {
        return postRepository.findById(String.valueOf(id))
                .map(PostMapping::convertEntityToPostResponse)
                .orElseThrow(() -> new NotFoundException("Post not found with id: " + id));
    }

    @Override
    @Transactional
    public PostResponse updatePosts(Long id, PostRequest request) {
        PostEntity existingEntity = postRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new NotFoundException("Post not found with id: " + id));
        existingEntity.setTitle(request.getTitle());
        existingEntity.setContent(request.getContent());
        PostEntity savedEntity = postRepository.save(existingEntity);
        return PostMapping.convertEntityToPostResponse(savedEntity);
    }

    @Override
    @Transactional
    public void deletePost(Long id) {
        if (!postRepository.existsById(String.valueOf(id))) {
            throw new NotFoundException("Post not found with id: " + id);
        }
        postRepository.deleteById(String.valueOf(id));
    }
}
