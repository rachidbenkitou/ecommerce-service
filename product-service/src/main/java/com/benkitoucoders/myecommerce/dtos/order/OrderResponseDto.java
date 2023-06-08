package com.benkitoucoders.myecommerce.dtos.order;

import com.benkitoucoders.myecommerce.entities.Address;
import com.benkitoucoders.myecommerce.enums.OrderStatus;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@Builder
public class OrderResponseDto implements Serializable {
    private Long id;
    private Date date;
    private OrderStatus orderStatus;
    private Address address;
}
