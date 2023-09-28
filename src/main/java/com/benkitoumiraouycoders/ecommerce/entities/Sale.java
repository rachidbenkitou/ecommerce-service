package com.benkitoumiraouycoders.ecommerce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    private String address;
    private String phone;
    private String email;
    private SaleStatus saleStatus;
    private LocalDateTime dateCreation;
    private LocalDateTime dateUpdate;
    private Double totalPrice;
}
