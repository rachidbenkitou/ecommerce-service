package com.benkitoucoders.ecommerce.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImageDto {
    private Long id;

    private String url;

    private Long productId;

    public ImageDto(Long id, String url, Long productId) {
        this.id = id;
        this.url = url;
        this.productId = productId;
    }

}
