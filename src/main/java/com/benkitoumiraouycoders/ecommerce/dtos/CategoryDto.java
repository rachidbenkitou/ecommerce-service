package com.benkitoumiraouycoders.ecommerce.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
//@NoArgsConstructor
public class CategoryDto implements Serializable {

    CategoryDto() {
    }

    private Long id;

    private String name;

    private String visbility;

    private String categoryImagePath;

    private String categoryImageUrl;

    public CategoryDto(Long id, String name, String visibility, String categoryImagePath) {
        this.id = id;
        this.name = name;
        this.visbility = visibility;
        this.categoryImagePath = categoryImagePath;
    }

}
