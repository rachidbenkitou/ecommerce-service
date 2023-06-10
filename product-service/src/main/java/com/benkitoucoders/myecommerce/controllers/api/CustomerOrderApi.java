package com.benkitoucoders.myecommerce.controllers.api;

import com.benkitoucoders.myecommerce.dtos.order.customerorder.CustomerOrderRequestDto;
import com.benkitoucoders.myecommerce.dtos.order.customerorder.CustomerOrderResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CustomerOrderApi {

    ResponseEntity<List<CustomerOrderResponseDto>> getCustomerOrders();

    ResponseEntity<CustomerOrderResponseDto> getCustomerOrderById(@PathVariable Long customerOrderId);

    ResponseEntity<CustomerOrderResponseDto> addCustomerOrder(@RequestBody @Valid CustomerOrderRequestDto customerOrderRequestDto);

    ResponseEntity<CustomerOrderResponseDto> updateCustomerOrder(@RequestBody @Valid CustomerOrderRequestDto customerOrderRequestDto);

    ResponseEntity<Void> deleteCustomerOrderById(@PathVariable Long customerOrderId);
}
