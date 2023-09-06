package com.benkitoumiraouycoders.ecommerce.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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
    private LocalDateTime dateCreation;
    private Long userCreation;
    private LocalDateTime dateUpdate;
    private Long userUpdate;
    private String cni;
    private String isPayed;
    private String address;
    private  String phone ;
    private String email;
    private SaleStatus saleStatus;

   /*  @Column(name = "status_id")
    private Long statusId;
    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "ID", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private SaleStatus saleStatus;*/


}
