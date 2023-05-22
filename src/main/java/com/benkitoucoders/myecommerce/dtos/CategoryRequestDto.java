package com.benkitoucoders.myecommerce.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CategoryRequestDto implements Serializable {
    @NotBlank(message = "The category name should not be null or empty")
    @Size(min = 1, max = 20, message = "The category name must be between 1 and 20 characters")
    private String name;
}
