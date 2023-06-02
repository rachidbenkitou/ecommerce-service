package com.benkitoucoders.myecommerce.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@Builder
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    //, cascade = CascadeType.REMOVE
    @OneToMany(mappedBy = "category")
    private List<SubCategory> subCategories;
}
