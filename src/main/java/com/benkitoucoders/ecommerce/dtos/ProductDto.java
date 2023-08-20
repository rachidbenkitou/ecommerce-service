package com.benkitoucoders.ecommerce.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;

    private String name;

    private String description;

    private double price;

    private int quantity;

    private Long categoryId;

    private String categoryName;

    private LocalDate dateCreated;

    private LocalDate dateUpdated;

}
