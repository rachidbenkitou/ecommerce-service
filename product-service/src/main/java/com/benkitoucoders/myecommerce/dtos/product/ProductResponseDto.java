package com.benkitoucoders.myecommerce.dtos.product;

import com.benkitoucoders.myecommerce.entities.Image;
import com.benkitoucoders.myecommerce.entities.SubCategory;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@Builder
public class ProductResponseDto implements Serializable {
    private int id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private Date dateCreated;
    private Date dateUpdated;
    private SubCategory subCategory;
    private List<Image> images;
}
