package com.benkitoucoders.ecommerce.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDto {

    private Long id;

    private String name;

    public CategoryDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
