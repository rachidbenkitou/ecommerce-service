package com.benkitoucoders.myecommerce.controllers;


import com.benkitoucoders.myecommerce.controllers.api.CustomerApi;
import com.benkitoucoders.myecommerce.dtos.customer.CustomerRequestDto;
import com.benkitoucoders.myecommerce.dtos.customer.CustomerResponseDto;
import com.benkitoucoders.myecommerce.services.serviceinterfaces.CustomerService;
import com.benkitoucoders.myecommerce.util.ObjectFormat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CustomersController implements CustomerApi {
    private final CustomerService customerService;

    @Override
    public ResponseEntity<List<CustomerResponseDto>> getCustomers() {
        List<CustomerResponseDto> customers = customerService.getCustomers();
        log.info("CustomerController::getCustomers response {}", ObjectFormat.jsonAsString(customers));
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CustomerResponseDto> getCustomerById(Long customerId) {
        CustomerResponseDto customerResponseDto = customerService.getCustomerById(customerId);
        log.info("CustomerController::getCustomer response {}", ObjectFormat.jsonAsString(customerResponseDto));
        return new ResponseEntity<>(customerResponseDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CustomerResponseDto> addCustomer(CustomerRequestDto customerRequestDto) {
        CustomerResponseDto customerResponseDto = customerService.createNewCustomer(customerRequestDto);
        return processCustomer(customerResponseDto, "addCustomer", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CustomerResponseDto> updateCustomer(CustomerRequestDto customerRequestDto) {

        CustomerResponseDto customerResponseDto = customerService.updateCustomer(customerRequestDto);
        return processCustomer(customerResponseDto, "updateCustomer", HttpStatus.OK);
    }

    private ResponseEntity<CustomerResponseDto> processCustomer(CustomerResponseDto customerResponseDto, String customerFunctionName, HttpStatus httpStatus) {
        log.info(String.format("CustomerController::%s response {}", customerFunctionName), ObjectFormat.jsonAsString(customerResponseDto));
        return new ResponseEntity<>(customerResponseDto, httpStatus);
    }

    @Override
    public ResponseEntity<Void> deleteCustomerById(Long customerId) {
        customerService.deteteCustomerById(customerId);
        log.info(String.format("CustomerController::CustomerController customer %s deleted", customerId));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
