package aibles.userprofilemanager_1.controller;

import aibles.userprofilemanager_1.dto.ImageRequest;
import aibles.userprofilemanager_1.dto.ImageResponse;

import aibles.userprofilemanager_1.service.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/images")
@Slf4j
public class ImageController {
    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ImageResponse createImage(@RequestBody ImageRequest request) {
        return imageService.saveImage(request);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ImageResponse getImageById(@PathVariable Long id) {
        return imageService.getImageById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ImageResponse> getAllImages() {
        return imageService.getAllImages();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ImageResponse updateImage(@PathVariable Long id, @RequestBody ImageRequest request) {
        return imageService.updateImage(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteImage(@PathVariable Long id) {
        imageService.deleteImage(id);
    }
}
