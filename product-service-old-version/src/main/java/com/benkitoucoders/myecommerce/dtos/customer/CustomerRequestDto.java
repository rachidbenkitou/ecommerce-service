package com.benkitoucoders.myecommerce.dtos.customer;

import com.benkitoucoders.myecommerce.dtos.user.UserRequestDto;
import com.benkitoucoders.myecommerce.entities.order.CustomerOrder;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class CustomerRequestDto extends UserRequestDto implements Serializable {
    private List<CustomerOrder> customerOrders;

}
