package com.benkitoucoders.myecommerce.entities.productcustomerorder;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProductCustomerOrderKey implements Serializable {
    private Long productId;
    private Long customerOrderId;
}
