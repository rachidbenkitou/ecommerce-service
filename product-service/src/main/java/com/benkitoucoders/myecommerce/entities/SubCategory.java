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
public class SubCategory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy = "subCategory")
    private List<Product> products;
    @ManyToOne
    private Category category;
}
