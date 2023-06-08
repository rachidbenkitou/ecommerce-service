package com.benkitoucoders.myecommerce.entities.order;

import com.benkitoucoders.myecommerce.entities.Address;
import com.benkitoucoders.myecommerce.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", length = 8)
@Entity
@Table(name = "my_order")
public abstract class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToOne
    private Address address;

}
