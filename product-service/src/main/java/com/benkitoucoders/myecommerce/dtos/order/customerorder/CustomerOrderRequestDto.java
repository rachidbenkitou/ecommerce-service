package com.benkitoucoders.myecommerce.dtos.order.customerorder;

import com.benkitoucoders.myecommerce.dtos.order.OrderRequestDto;
import com.benkitoucoders.myecommerce.entities.productorder.ProductCustomerOrder;
import lombok.*;

import java.io.Serializable;
import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class CustomerOrderRequestDto extends OrderRequestDto implements Serializable {
    private Set<ProductCustomerOrder> customerOrders;

}
