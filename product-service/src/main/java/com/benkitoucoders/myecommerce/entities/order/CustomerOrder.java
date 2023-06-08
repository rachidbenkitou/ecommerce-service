package com.benkitoucoders.myecommerce.entities.order;

import com.benkitoucoders.myecommerce.entities.productorder.ProductCustomerOrder;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@DiscriminatorValue("customer")
@Entity
public class CustomerOrder extends Order implements Serializable {

    @OneToMany(mappedBy = "customerOrder")
    private Set<ProductCustomerOrder> customerOrders;

}