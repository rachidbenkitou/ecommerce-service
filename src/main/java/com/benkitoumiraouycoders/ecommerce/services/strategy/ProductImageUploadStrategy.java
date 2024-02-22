package com.benkitoumiraouycoders.ecommerce.services.strategy;

import com.benkitoumiraouycoders.ecommerce.dao.ImageDao;
import com.benkitoumiraouycoders.ecommerce.entities.Image;
import com.benkitoumiraouycoders.ecommerce.exceptions.EntityAlreadyExistsException;
import com.benkitoumiraouycoders.ecommerce.mappers.ImageMapper;
import com.benkitoumiraouycoders.ecommerce.services.strategy.inter.ImageUploadStrategy;
import com.benkitoumiraouycoders.ecommerce.services.strategy.inter.ImagesUploadStrategy;
import com.benkitoumiraouycoders.ecommerce.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductImageUploadStrategy implements ImageUploadStrategy, ImagesUploadStrategy {

    private final ImageDao imageDao;
    private final ImageMapper imageMapper;

    @Override
    public void uploadImage(MultipartFile file, Long productId) throws IOException {
        String folderPath = Constants.IMAGE_FOLDER_PATH + "products/product_" + productId + "/";
        String filePath = folderPath + file.getOriginalFilename();

        Optional<Image> existingImage = imageDao.findByFilePath(filePath);
        existingImage.ifPresent(image -> {
            throw new EntityAlreadyExistsException(String.format("This image with name %s already exists", file.getOriginalFilename()));
        });

        Image image = imageDao.save(
                Image.builder()
                        .name(file.getOriginalFilename())
                        .productId(productId)
                        .categoryId(null)
                        .type(file.getContentType())
                        .filePath(filePath).build());

        // Create the product's folder if it doesn't exist
        Path path = Paths.get(folderPath);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        if (image != null) {
            file.transferTo(new File(filePath));
            //return "file uploaded successfully: " + filePath;
        } else {
            //return "Failed to save image entity.";
        }
    }

    @Override
    public void uploadImages(List<MultipartFile> images, Long productId) throws IOException {
        for (MultipartFile image : images) {
            uploadImage(image, productId);
        }
    }
}
