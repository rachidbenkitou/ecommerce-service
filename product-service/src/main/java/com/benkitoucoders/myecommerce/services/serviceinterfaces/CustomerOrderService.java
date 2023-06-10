package com.benkitoucoders.myecommerce.services.serviceinterfaces;

import com.benkitoucoders.myecommerce.dtos.order.customerorder.CustomerOrderRequestDto;
import com.benkitoucoders.myecommerce.dtos.order.customerorder.CustomerOrderResponseDto;

import java.util.List;

public interface CustomerOrderService {
    List<CustomerOrderResponseDto> getCustomerOrders();

    CustomerOrderResponseDto getCustomerOrderById(Long customerOrderId);

    CustomerOrderResponseDto createNewCustomerOrder(CustomerOrderRequestDto customerOrderRequestDto);

    CustomerOrderResponseDto updateCustomerOrder(CustomerOrderRequestDto customerOrderRequestDto);

    void deteteCustomerOrderById(Long customerOrderId);
}
