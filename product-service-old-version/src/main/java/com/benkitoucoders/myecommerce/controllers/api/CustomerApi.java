package com.benkitoucoders.myecommerce.controllers.api;

import com.benkitoucoders.myecommerce.dtos.customer.CustomerRequestDto;
import com.benkitoucoders.myecommerce.dtos.customer.CustomerResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CustomerApi {
    ResponseEntity<List<CustomerResponseDto>> getCustomers();

    ResponseEntity<CustomerResponseDto> getCustomerById(@PathVariable Long customerId);

    ResponseEntity<CustomerResponseDto> addCustomer(@RequestBody @Valid CustomerRequestDto customerRequestDto);

    ResponseEntity<CustomerResponseDto> updateCustomer(@RequestBody @Valid CustomerRequestDto customerRequestDto);

    ResponseEntity<Void> deleteCustomerById(@PathVariable Long customerId);

}
