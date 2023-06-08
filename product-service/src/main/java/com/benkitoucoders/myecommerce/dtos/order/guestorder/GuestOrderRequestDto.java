package com.benkitoucoders.myecommerce.dtos.order.guestorder;

import com.benkitoucoders.myecommerce.dtos.order.OrderResponseDto;
import com.benkitoucoders.myecommerce.entities.productorder.ProductGuestOrder;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class GuestOrderRequestDto extends OrderResponseDto implements Serializable {
    private Set<ProductGuestOrder> productGuestOrders;

}
