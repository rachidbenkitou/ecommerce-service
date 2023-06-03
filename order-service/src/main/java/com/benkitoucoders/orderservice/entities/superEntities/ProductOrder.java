package com.benkitoucoders.orderservice.entities.superEntities;

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
    private Date orderDate;
}
