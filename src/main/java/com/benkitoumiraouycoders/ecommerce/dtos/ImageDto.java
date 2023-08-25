package com.benkitoumiraouycoders.ecommerce.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImageDto {
    private Long id;

    private String name;

    private String type;

    private String filePath;

    private Long productId;

    public ImageDto(Long id, String name, String type ,String filePath, Long productId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.filePath = filePath;
        this.productId = productId;
    }

}
