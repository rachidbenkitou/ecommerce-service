package com.benkitoumiraouycoders.ecommerce.entities;

import com.benkitoumiraouycoders.ecommerce.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ClientOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    private String discription;
    private LocalDateTime dateCreation;
    private LocalDateTime dateUpdate;
    private Long userCreation;
    private Long userUpdate;
    private OrderStatus orderStatus;


}
