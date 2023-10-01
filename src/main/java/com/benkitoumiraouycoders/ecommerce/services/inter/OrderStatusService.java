package com.benkitoumiraouycoders.ecommerce.services.inter;

import com.benkitoumiraouycoders.ecommerce.dtos.OrderStatusDto;

import java.util.List;

public interface OrderStatusService {
    List<OrderStatusDto> getOrderStatus();

}
