package com.benkitoucoders.myecommerce.dtos.category;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@Builder
public class CategoryRequestDto implements Serializable {
    private int id;
    @NotBlank(message = "The category name should not be null or empty")
    @Size(min = 1, max = 20, message = "The category name must be between 1 and 20 characters")
    @Column(unique = true)
    private String name;
}
