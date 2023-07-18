package com.benkitoucoders.myecommerce.dtos.customer;

import com.benkitoucoders.myecommerce.dtos.user.UserResponseDto;
import com.benkitoucoders.myecommerce.entities.order.CustomerOrder;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class CustomerResponseDto extends UserResponseDto {
    private List<CustomerOrder> customerOrders;

}
