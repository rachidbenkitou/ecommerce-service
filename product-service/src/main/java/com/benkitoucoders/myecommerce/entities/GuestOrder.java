package com.benkitoucoders.myecommerce.entities;

import com.benkitoucoders.myecommerce.entities.superentities.Order;
import com.benkitoucoders.myecommerce.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@Entity
public class GuestOrder extends Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @Column(unique = true)
    private Address address;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private Date date;


}
