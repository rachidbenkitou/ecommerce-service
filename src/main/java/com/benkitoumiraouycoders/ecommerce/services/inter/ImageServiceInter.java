package com.benkitoumiraouycoders.ecommerce.services.inter;

import com.benkitoumiraouycoders.ecommerce.dtos.ImageDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageServiceInter {
    List<ImageDto> getImagesByQuery(Long imageId, String imageName, String imageType, String imageFilePath, Long productId, Long categoryId);

    ImageDto getImageById(Long id);

    ImageDto addImage(ImageDto imageDto);

    ImageDto updateImage(Long id, ImageDto imageDto);

    void deleteImageById(Long id);

    String uploadImageToFileSystem(MultipartFile file, Long productId) throws IOException;

    void uploadImagesToFileSystem(List<MultipartFile> images, Long productId) throws IOException;
}
