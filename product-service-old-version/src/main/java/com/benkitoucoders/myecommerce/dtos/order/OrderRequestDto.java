package com.benkitoucoders.myecommerce.dtos.order;

import com.benkitoucoders.myecommerce.entities.Address;
import com.benkitoucoders.myecommerce.enums.OrderStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class OrderRequestDto implements Serializable {
    private Long id;
    @NotBlank(message = "The order date should not be null or empty")
    private Date date;
    @NotBlank(message = "The order date should not be null or empty")
    private OrderStatus orderStatus;
    @NotBlank(message = "The order address should not be null or empty")
    private Address address;
}
