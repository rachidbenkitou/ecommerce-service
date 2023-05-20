package com.benkitoucoders.myecommerce.dtos;

import com.benkitoucoders.myecommerce.entities.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductResponseDto implements Serializable {
    private int id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private Date dateCreated;
    private Date dateUpdated;
    private List<Image> images;
}
