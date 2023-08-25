package com.benkitoumiraouycoders.ecommerce.controllers;

import com.benkitoumiraouycoders.ecommerce.dtos.ImageDto;
import com.benkitoumiraouycoders.ecommerce.services.inter.ImageServiceInter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageServiceInter imageService;

    @GetMapping
    public ResponseEntity<List<ImageDto>> getImagesByQuery(@RequestParam(name = "imageId", required = false) Long imageId,
                                                           @RequestParam(name = "imageName", required = false) String imageName,
                                                           @RequestParam(name = "imageType", required = false) String imageType,
                                                           @RequestParam(name = "imageFilePath", required = false) String imageFilePath,
                                                           @RequestParam(name = "productId", required = false) Long productId) {
        return ResponseEntity.ok().body(imageService.getImagesByQuery(imageId, imageName, imageType, imageFilePath, productId));
    }

    @GetMapping("/{imageId}")
    public ResponseEntity<ImageDto> getImageById(@PathVariable Long imageId) {
        return ResponseEntity.ok().body(imageService.getImageById(imageId));
    }

    @PostMapping
    public ResponseEntity<ImageDto> addImage(@RequestBody ImageDto imageDto) {
        return ResponseEntity.ok().body(imageService.addImage(imageDto));
    }

    @PutMapping("/{imageId}")
    public ResponseEntity<ImageDto> updateImage(@PathVariable Long imageId, @RequestBody ImageDto imageDto) {
        return ResponseEntity.ok().body(imageService.updateImage(imageId, imageDto));
    }

    @DeleteMapping("/{imageId}")
    public ResponseEntity<String> deleteImageById(@PathVariable Long imageId) {
        imageService.deleteImageById(imageId);
        return ResponseEntity.ok().body("The category has been deleted successfully.");
    }

    @PostMapping("/uploadImages")
    public ResponseEntity<String> uploadImages(
            @RequestParam(name = "images", required = true) List<MultipartFile> images,
            @RequestParam(name = "productId", required = true) Long productId) throws IOException {
        imageService.uploadImagesToFileSystem(images, productId);
        return ResponseEntity.ok("Images uploaded successfully!");
    }

}
