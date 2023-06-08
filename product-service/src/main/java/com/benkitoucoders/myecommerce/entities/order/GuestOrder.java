package com.benkitoucoders.myecommerce.entities.order;

import com.benkitoucoders.myecommerce.entities.productorder.ProductGuestOrder;
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
@DiscriminatorValue("guest")
@EqualsAndHashCode(callSuper = false)
@Entity
public class GuestOrder extends Order implements Serializable {

    @OneToMany(mappedBy = "guestOrder")
    private Set<ProductGuestOrder> productGuestOrders;

}
