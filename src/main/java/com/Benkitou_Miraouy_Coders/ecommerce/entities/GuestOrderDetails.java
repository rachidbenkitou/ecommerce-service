package com.Benkitou_Miraouy_Coders.ecommerce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class GuestOrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long guestOrderId;
    private Long productId;
    private LocalDateTime dateCreation;
    private Long userCreation;

    
}
