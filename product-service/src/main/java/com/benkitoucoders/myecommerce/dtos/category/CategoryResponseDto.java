package com.benkitoucoders.myecommerce.dtos.category;

import com.benkitoucoders.myecommerce.entities.SubCategory;
import lombok.*;

import java.io.Serializable;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@Builder
public class CategoryResponseDto implements Serializable {
    private int id;
    private String name;
    private List<SubCategory> subCategories;
}
