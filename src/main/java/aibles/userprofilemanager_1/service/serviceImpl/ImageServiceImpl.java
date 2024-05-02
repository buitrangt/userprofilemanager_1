package aibles.userprofilemanager_1.service.serviceImpl;

import aibles.userprofilemanager_1.dto.request.ImageRequest;
import aibles.userprofilemanager_1.dto.response.ImageResponse;
import aibles.userprofilemanager_1.entity.ImageEntity;
import aibles.userprofilemanager_1.entity.PostEntity;
import aibles.userprofilemanager_1.exception.NotFoundException;
import aibles.userprofilemanager_1.repository.ImageRepository;
import aibles.userprofilemanager_1.repository.PostRepository;
import aibles.userprofilemanager_1.service.mapping.ImageMapping;
import aibles.userprofilemanager_1.service.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final PostRepository postRepository;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository, PostRepository postRepository) {
        this.imageRepository = imageRepository;
        this.postRepository = postRepository;
    }

    @Override
    @Transactional
    public ImageResponse saveImage(ImageRequest request) {
        // Lấy postId từ cơ sở dữ liệu
        String postId = ""; // Khởi tạo postId

        // Thực hiện truy vấn để lấy bài post từ cơ sở dữ liệu
        Optional<PostEntity> postEntity = postRepository.findById(postId); // Thay thế bằng các tiêu chí để lấy bài post, ví dụ findByTitle(request.getTitle()) hoặc findByAuthor(request.getAuthor())

        // Kiểm tra xem postEntity có tồn tại hay không
        if (postEntity.isPresent()) {
            // Nếu tồn tại, gán postId bằng id của bài post
            postId = postEntity.get().getId();
        } else {
            // Nếu không tồn tại, có thể xử lý trường hợp không tìm thấy bài post ở đây
            // Ví dụ: Throw một NotFoundException hoặc trả về một ImageResponse lỗi
        }

        // Chuyển đổi ImageRequest thành ImageEntity và lưu vào cơ sở dữ liệu
        ImageEntity entity = ImageMapping.convertDtoToEntity(postId, request);
        ImageEntity savedEntity = imageRepository.save(entity);

        // Chuyển đổi ImageEntity đã lưu thành ImageResponse và trả về
        return ImageMapping.convertEntityToImageResponse(savedEntity);
    }

    @Override
    public ImageResponse getImageById(Long id) {
        return imageRepository.findById(String.valueOf(id))
                .map(ImageMapping::convertEntityToImageResponse)
                .orElseThrow(() -> new NotFoundException("Image not found with ID: " + id));
    }

    @Override
    public List<ImageResponse> getAllImages() {
        return imageRepository.findAll().stream()
                .map(ImageMapping::convertEntityToImageResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ImageResponse updateImage(Long id, ImageRequest request) {
        ImageEntity entity = imageRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new NotFoundException("Image not found with ID: " + id));
        updateEntityFields(entity, request);
        imageRepository.save(entity);
        return ImageMapping.convertEntityToImageResponse(entity);
    }

    private void updateEntityFields(ImageEntity entity, ImageRequest request) {
        entity.setUrl(request.getUrl());
        // Add more fields to update as necessary
    }

    @Override
    @Transactional
    public void deleteImage(Long id) {
        if (!imageRepository.existsById(String.valueOf(id))) {
            throw new NotFoundException("Image not found with ID: " + id);
        }
        imageRepository.deleteById(String.valueOf(id));
    }
}
