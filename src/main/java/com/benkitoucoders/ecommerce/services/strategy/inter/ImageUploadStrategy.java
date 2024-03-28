package com.benkitoucoders.ecommerce.services.strategy.inter;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageUploadStrategy {
    void uploadImage(MultipartFile file, Long entityId) throws IOException;
}
