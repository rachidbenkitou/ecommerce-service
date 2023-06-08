package com.benkitoucoders.myecommerce.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@Builder
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private Date dateCreated;
    private Date dateUpdated;
    @ManyToOne
    private SubCategory subCategory;
    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Image> images;
}
