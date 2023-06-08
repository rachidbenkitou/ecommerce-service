package com.benkitoucoders.myecommerce.entities.superentities;

import com.benkitoucoders.myecommerce.enums.OrderStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class Order {
    private Date date;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
