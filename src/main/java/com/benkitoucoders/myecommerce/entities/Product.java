package com.benkitoucoders.myecommerce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private Date dateCreated;
    private Date dateUpdated;
    @ManyToOne
    private SubCategory subCategory;
    @OneToMany
    private List<Image> images;
}
