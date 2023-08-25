package com.benkitoumiraouycoders.ecommerce.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class GuestOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    private String discription;
    private LocalDateTime dateCreation;
    private LocalDateTime dateUpdate;
    private Long userCreation;
    private Long userUpdate;
    private GuestOrderStatus guestOrderStatus;


}
