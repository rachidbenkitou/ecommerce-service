package com.benkitoucoders.myecommerce.dtos.subCategory;

import com.benkitoucoders.myecommerce.entities.Category;
import com.benkitoucoders.myecommerce.entities.Product;
import lombok.*;

import java.io.Serializable;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@Builder
public class SubCategoryResponseDto implements Serializable {
    private int id;
    private String name;
    private List<Product> products;
    private Category category;
}
