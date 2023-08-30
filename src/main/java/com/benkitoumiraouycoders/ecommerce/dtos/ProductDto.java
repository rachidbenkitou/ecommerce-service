package com.benkitoumiraouycoders.ecommerce.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ProductDto {

    private Long id;

    private String name;

    private String description;

    private Double price;

    private Double comparePrice;

    private Integer quantity;

    private String visibility;

    private Long categoryId;

    private String categoryName;

    private LocalDate dateCreated;

    private LocalDate dateUpdated;

    public ProductDto(Long id, String name, String description, Double price, Double comparePrice, Integer quantity, String visibility, Long categoryId, String categoryName, LocalDate dateCreated, LocalDate dateUpdated) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.comparePrice = comparePrice;
        this.quantity = quantity;
        this.visibility=visibility;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
    }
}
