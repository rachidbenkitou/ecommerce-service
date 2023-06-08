package com.benkitoucoders.myecommerce.entities;

import com.benkitoucoders.myecommerce.entities.superentities.Order;
import com.benkitoucoders.myecommerce.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@Entity
public class CustomerOrder extends Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Customer customer;
}
