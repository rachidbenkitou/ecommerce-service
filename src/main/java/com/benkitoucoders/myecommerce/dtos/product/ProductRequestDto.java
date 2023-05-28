package com.benkitoucoders.myecommerce.dtos.product;

import com.benkitoucoders.myecommerce.entities.Image;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
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
    @NotBlank(message = "The price is required")
    private double price;

    @Positive(message = "The quantity must be a positive value")
    @NotBlank(message = "The quantity is required")
    private int quantity;

    @NotBlank(message = "Images are required")
    private List<Image> images;
}
