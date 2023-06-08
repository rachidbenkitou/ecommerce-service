package com.benkitoucoders.myecommerce.entities.superentities;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class ProductOrder extends Order implements Serializable {
    private int quantity;
    private double totalPrice;
}
