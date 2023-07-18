package com.benkitoucoders.myecommerce.services.serviceinterfaces;

import com.benkitoucoders.myecommerce.dtos.customer.CustomerRequestDto;
import com.benkitoucoders.myecommerce.dtos.customer.CustomerResponseDto;

import java.util.List;

public interface CustomerService {
    List<CustomerResponseDto> getCustomers();

    CustomerResponseDto getCustomerById(Long customerId);

    CustomerResponseDto createNewCustomer(CustomerRequestDto customerRequestDto);

    CustomerResponseDto updateCustomer(CustomerRequestDto customerRequestDto);

    void deteteCustomerById(Long customerId);

}
