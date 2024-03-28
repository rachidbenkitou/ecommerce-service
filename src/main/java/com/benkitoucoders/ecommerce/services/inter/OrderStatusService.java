package com.benkitoucoders.ecommerce.services.inter;

import com.benkitoucoders.ecommerce.dtos.OrderStatusDto;

import java.util.List;

public interface OrderStatusService {
    List<OrderStatusDto> getOrderStatus();

}
