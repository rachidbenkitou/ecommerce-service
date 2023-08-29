package com.benkitoumiraouycoders.ecommerce.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDto {

    private Long id;

    private String name;

    private String visbility;


    public CategoryDto(Long id, String name, String visibility) {
        this.id = id;
        this.name = name;
        this.visbility=visibility;
    }

}
