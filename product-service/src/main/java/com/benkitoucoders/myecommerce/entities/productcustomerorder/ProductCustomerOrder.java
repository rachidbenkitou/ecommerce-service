package com.benkitoucoders.myecommerce.entities.productcustomerorder;

import com.benkitoucoders.myecommerce.entities.CustomerOrder;
import com.benkitoucoders.myecommerce.entities.Product;
import com.benkitoucoders.myecommerce.entities.superentities.ProductOrder;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
public class ProductCustomerOrder extends ProductOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @EmbeddedId
    private ProductCustomerOrderKey productCustomerOrderKey;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn
    private Product product;

    @ManyToOne
    @MapsId("customerOrderId")
    @JoinColumn
    private CustomerOrder customerOrder;

}
