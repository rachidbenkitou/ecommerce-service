package com.benkitoucoders.orderservice.entities.superEntities;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class Order {
    private double totalPrice;
}
