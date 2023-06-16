package com.benkitoucoders.myecommerce.dtos.product;

import com.benkitoucoders.myecommerce.entities.Image;
import com.benkitoucoders.myecommerce.entities.SubCategory;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@Builder
public class ProductRequestDto implements Serializable {

    private int id;

    @NotBlank(message = "The product name should not be null or empty")
    @Size(min = 1, max = 20, message = "The product name must be between 1 and 20 characters")
    private String name;

    @NotBlank(message = "The description is required")
    @Size(max = 100, message = "The description must not exceed 100 characters")
    private String description;

    @Positive(message = "The price must be a positive value")
    private double price;

    @Positive(message = "The quantity must be a positive value")
    @Min(value = 1, message = "The quantity must be at least 1")
    private int quantity;

    private Date dateCreated;

    private Date dateUpdated;

    private SubCategory subCategory;

    private List<Image> images;
}
