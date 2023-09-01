package com.benkitoumiraouycoders.ecommerce.services.strategy;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageUploadStrategy {
    void uploadImage(MultipartFile file, Long entityId) throws IOException;
}
