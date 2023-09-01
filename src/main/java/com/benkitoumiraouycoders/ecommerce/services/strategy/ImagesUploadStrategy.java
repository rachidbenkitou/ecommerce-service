package com.benkitoumiraouycoders.ecommerce.services.strategy;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImagesUploadStrategy {
    void uploadImages(List<MultipartFile> files, Long entityId) throws IOException;

}
