package com.benkitoucoders.myecommerce.services;

import com.benkitoucoders.myecommerce.daos.CustomerOrderDao;
import com.benkitoucoders.myecommerce.dtos.order.customerorder.CustomerOrderRequestDto;
import com.benkitoucoders.myecommerce.dtos.order.customerorder.CustomerOrderResponseDto;
import com.benkitoucoders.myecommerce.entities.order.CustomerOrder;
import com.benkitoucoders.myecommerce.exceptions.customer.CustomerAlreadyExistsException;
import com.benkitoucoders.myecommerce.exceptions.customerorder.CustomerOrderAlreadyExistsException;
import com.benkitoucoders.myecommerce.exceptions.customerorder.CustomerOrderNotFoundException;
import com.benkitoucoders.myecommerce.mappers.CustomerOrderMapper;
import com.benkitoucoders.myecommerce.services.serviceinterfaces.CustomerOrderService;
import com.benkitoucoders.myecommerce.util.ObjectFormat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CustomerOrderServiceImpl implements CustomerOrderService {
    private final CustomerOrderDao customerOrderDao;
    private final CustomerOrderMapper customerOrderMapper;

    @Override
    public List<CustomerOrderResponseDto> getCustomerOrders() {
        List<CustomerOrderResponseDto> customerOrderResponseDtos = null;
        log.info("CustomerOrderService:getCustomerOrders execution started.");
        List<CustomerOrder> customerOrderList = customerOrderDao.findAll();
        if (!customerOrderList.isEmpty()) {
            customerOrderResponseDtos = customerOrderList.stream()
                    .map(customerOrderMapper::modelToDto)
                    .toList();
        } else {
            customerOrderResponseDtos = Collections.emptyList();
        }
        log.debug("CustomerOrderService:getCustomers retrieving customerOrder from database  {}", ObjectFormat.jsonAsString(customerOrderResponseDtos));
        log.info("CustomerService:getCustomers execution ended.");
        return customerOrderResponseDtos;
    }

    @Override
    public CustomerOrderResponseDto getCustomerOrderById(Long customerOrderId) {
        CustomerOrderResponseDto customerOrderResponseDto;
        log.info("CustomerOrderService:getCustomerOrderById execution started.");
        CustomerOrder customerOrder = customerOrderDao.findById(customerOrderId)
                .orElseThrow(() -> new CustomerOrderNotFoundException(String.format("CustomerOrder with id %d is not found", customerOrderId)));
        customerOrderResponseDto = customerOrderMapper.modelToDto(customerOrder);
        log.debug("CustomerOrderService:getCustomerOrderById retrieving customerOrder from database for id {} {}", customerOrderId, ObjectFormat.jsonAsString(customerOrderResponseDto));
        log.info("CustomerOrderService:getCustomerOrderById execution ended.");
        return customerOrderResponseDto;
    }

    @Override
    public CustomerOrderResponseDto createNewCustomerOrder(CustomerOrderRequestDto customerOrderRequestDto) {
        return processCustomerOrder(customerOrderRequestDto, "createNewCustomerOrder");
    }

    @Override
    public CustomerOrderResponseDto updateCustomerOrder(CustomerOrderRequestDto customerOrderRequestDto) {
        return processCustomerOrder(customerOrderRequestDto, "updateCustomerOrder");
    }

    private CustomerOrderResponseDto processCustomerOrder(CustomerOrderRequestDto customerOrderRequestDto, String customerOrderFunctionName) {
        CustomerOrderResponseDto customerOrderResponseDto;

        log.info(String.format("CustomerOrderService:%s execution started.", customerOrderFunctionName));
        CustomerOrder customerOrder = customerOrderMapper.dtoToModule(customerOrderRequestDto);
        log.debug(String.format("CustomerOrderService:%s request parameters {}", customerOrderFunctionName), ObjectFormat.jsonAsString(customerOrderRequestDto));

            /*
            To ensure the customerOrder's existence, we perform a verification step when creating a customerOrder.
            This verification is not necessary when updating a customerOrder since we already know it exists.
             */
        if (customerOrderFunctionName.equals("createNewCustomerOrder") && customerOrderDao.existsById(customerOrderRequestDto.getId()))
            throw new CustomerOrderAlreadyExistsException(String.format("The customerOrder with id %d is already exists.", customerOrderRequestDto.getId()));
        CustomerOrder customerOrderResults = customerOrderDao.save(customerOrder);
        customerOrderResponseDto = customerOrderMapper.modelToDto(customerOrderResults);
        log.debug(String.format("CustomerOrderService:%s received response from Database {}", customerOrderFunctionName), ObjectFormat.jsonAsString(customerOrderRequestDto));

        log.info(String.format("CustomerOrderService:%s execution ended.", customerOrderFunctionName));
        return customerOrderResponseDto;
    }

    @Override
    public void deteteCustomerOrderById(Long customerOrderId) {
        log.info("CustomerOrderService:deleteCustomerOrder execution started.");
        customerOrderDao.deleteById(customerOrderId);
        log.debug("CustomerOrderService:deleteCustomerOrder customerOrder is deleted from the Database");

        log.info("CustomerOrderService:deleteCustomerOrder execution ended.");
    }
}
