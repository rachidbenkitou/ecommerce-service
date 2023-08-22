package com.benkitoucoders.ecommerce.dtos;

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

    private Integer quantity;

    private Long categoryId;

    private String categoryName;

    private LocalDate dateCreated;

    private LocalDate dateUpdated;

    public ProductDto(Long id, String name, String description, Double price, Integer quantity, Long categoryId, String categoryName, LocalDate dateCreated, LocalDate dateUpdated) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
    }
}
