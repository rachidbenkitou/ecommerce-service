package com.benkitoucoders.myecommerce.controllers;


import com.benkitoucoders.myecommerce.controllers.api.CustomerOrderApi;
import com.benkitoucoders.myecommerce.dtos.order.customerorder.CustomerOrderRequestDto;
import com.benkitoucoders.myecommerce.dtos.order.customerorder.CustomerOrderResponseDto;
import com.benkitoucoders.myecommerce.services.serviceinterfaces.CustomerOrderService;
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
public class CustomerOrderController implements CustomerOrderApi {
    private final CustomerOrderService customerOrderService;

    @Override
    public ResponseEntity<List<CustomerOrderResponseDto>> getCustomerOrders() {
        List<CustomerOrderResponseDto> customerOrders = customerOrderService.getCustomerOrders();
        log.info("CustomerOrderController::getCustomerOrders response {}", ObjectFormat.jsonAsString(customerOrders));
        return new ResponseEntity<>(customerOrders, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CustomerOrderResponseDto> getCustomerOrderById(Long customerOrderId) {
        CustomerOrderResponseDto customerOrderResponseDto = customerOrderService.getCustomerOrderById(customerOrderId);
        log.info("CustomerOrderController::getCustomerOrder response {}", ObjectFormat.jsonAsString(customerOrderResponseDto));
        return new ResponseEntity<>(customerOrderResponseDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CustomerOrderResponseDto> addCustomerOrder(CustomerOrderRequestDto customerOrderRequestDto) {
        CustomerOrderResponseDto customerOrderResponseDto = customerOrderService.createNewCustomerOrder(customerOrderRequestDto);
        return processCustomerOrder(customerOrderResponseDto, "addCustomerOrder", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CustomerOrderResponseDto> updateCustomerOrder(CustomerOrderRequestDto customerOrderRequestDto) {
        CustomerOrderResponseDto customerOrderResponseDto = customerOrderService.updateCustomerOrder(customerOrderRequestDto);
        return processCustomerOrder(customerOrderResponseDto, "updateCustomerOrder", HttpStatus.OK);
    }

    private ResponseEntity<CustomerOrderResponseDto> processCustomerOrder(CustomerOrderResponseDto customerOrderResponseDto, String customerOrderFunctionName, HttpStatus httpStatus) {
        log.info(String.format("CustomerOrderController::%s response {}", customerOrderFunctionName), ObjectFormat.jsonAsString(customerOrderResponseDto));
        return new ResponseEntity<>(customerOrderResponseDto, httpStatus);
    }

    @Override
    public ResponseEntity<Void> deleteCustomerOrderById(Long customerOrderId) {
        customerOrderService.deteteCustomerOrderById(customerOrderId);
        log.info(String.format("CustomerOrderController::CustomerOrderController customerOrder %s deleted", customerOrderId));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
