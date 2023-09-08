package com.benkitoumiraouycoders.ecommerce.services.inter;

import com.benkitoumiraouycoders.ecommerce.dtos.ImageDto;
import com.benkitoumiraouycoders.ecommerce.handlers.ResponseDto;

import java.util.List;

public interface ImageService {
    List<ImageDto> getImagesByQuery(Long imageId, String imageName, String imageType, String imageFilePath, Long productId, Long categoryId);

    ImageDto getImageById(Long id);

    ImageDto addImage(ImageDto imageDto);

    ImageDto updateImage(Long id, ImageDto imageDto);

    ResponseDto deleteImageById(Long id);
    ResponseDto deleteImagesByProductId(Long productId);

    ResponseDto deleteImageByCategoryId(Long categoryId);
}
