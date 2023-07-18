package com.benkitoucoders.myecommerce.entities;

import com.benkitoucoders.myecommerce.entities.order.CustomerOrder;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@DiscriminatorValue("customer")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer extends User implements Serializable {
    @OneToMany
    private List<CustomerOrder> customerOrders;
}
