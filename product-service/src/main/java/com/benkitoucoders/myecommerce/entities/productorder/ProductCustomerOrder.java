package com.benkitoucoders.myecommerce.entities.productorder;

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
public class ProductCustomerOrder extends ProductOrder implements Serializable {

    @ManyToOne
    @JoinColumn
    private Product product;

    @ManyToOne
    @JoinColumn
    private CustomerOrder customerOrder;

}
