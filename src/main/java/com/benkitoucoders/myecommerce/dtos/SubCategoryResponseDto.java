package com.benkitoucoders.myecommerce.dtos;

import com.benkitoucoders.myecommerce.entities.Category;
import com.benkitoucoders.myecommerce.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SubCategoryResponseDto implements Serializable {
    private int id;
    private String name;
    private List<Product> products;
    private Category category;
}
