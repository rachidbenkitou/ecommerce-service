package com.benkitoumiraouycoders.ecommerce.services.inter;

import com.benkitoumiraouycoders.ecommerce.dtos.ImageDto;

import java.util.List;

public interface ImageServiceInter {
    List<ImageDto> getImagesByQuery(Long imageId, String imageName, String imageType, String imageFilePath, Long productId, Long categoryId);

    ImageDto getImageById(Long id);

    ImageDto addImage(ImageDto imageDto);

    ImageDto updateImage(Long id, ImageDto imageDto);

    void deleteImageById(Long id);
    void deleteImagesByProductId(Long productId);

}
