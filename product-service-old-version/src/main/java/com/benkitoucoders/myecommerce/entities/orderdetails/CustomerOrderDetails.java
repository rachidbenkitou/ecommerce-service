package com.benkitoucoders.myecommerce.entities.orderdetails;

import com.benkitoucoders.myecommerce.entities.Product;
import com.benkitoucoders.myecommerce.entities.order.CustomerOrder;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@DiscriminatorValue("customer")
@Entity
public class CustomerOrderDetails extends OrderDetails implements Serializable {

    @ManyToOne
    @JoinColumn
    private Product product;

    @ManyToOne
    @JoinColumn
    private CustomerOrder customerOrder;

}
