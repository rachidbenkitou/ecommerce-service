package com.benkitoucoders.myecommerce.entities.order;

import com.benkitoucoders.myecommerce.entities.Address;
import com.benkitoucoders.myecommerce.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.time.LocalDateTime;

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

    @CreatedDate
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToOne
    private Address address;

}
