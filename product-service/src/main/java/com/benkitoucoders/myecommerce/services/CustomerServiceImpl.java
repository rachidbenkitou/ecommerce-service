package com.benkitoucoders.myecommerce.services;

import com.benkitoucoders.myecommerce.daos.CustomerDao;
import com.benkitoucoders.myecommerce.dtos.customer.CustomerRequestDto;
import com.benkitoucoders.myecommerce.dtos.customer.CustomerResponseDto;
import com.benkitoucoders.myecommerce.entities.Customer;
import com.benkitoucoders.myecommerce.exceptions.customer.CustomerAlreadyExistsException;
import com.benkitoucoders.myecommerce.exceptions.customer.CustomerNotFoundException;
import com.benkitoucoders.myecommerce.mappers.CustomerMapper;
import com.benkitoucoders.myecommerce.services.serviceinterfaces.CustomerService;
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
public class CustomerServiceImpl implements CustomerService {
    private final CustomerDao customerDao;
    private final CustomerMapper customerMapper;

    @Override
    public List<CustomerResponseDto> getCustomers() {
        List<CustomerResponseDto> customerResponseDtos = null;
        log.info("CustomerService:getCustomers execution started.");
        List<Customer> customerList = customerDao.findAll();
        if (!customerList.isEmpty()) {
            customerResponseDtos = customerList.stream()
                    .map(customerMapper::modelToDto)
                    .toList();
        } else {
            customerResponseDtos = Collections.emptyList();
        }
        log.debug("CustomerService:getCustomers retrieving customers from database  {}", ObjectFormat.jsonAsString(customerResponseDtos));
        log.info("CustomerService:getCustomers execution ended.");
        return customerResponseDtos;
    }

    @Override
    public CustomerResponseDto getCustomerById(Long customerId) {
        CustomerResponseDto customerResponseDto;
        log.info("CustomerService:getCustomerById execution started.");
        Customer customer = customerDao.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("Customer with name %d is not found", customerId)));
        customerResponseDto = customerMapper.modelToDto(customer);
        log.debug("CustomerService:getCustomerById retrieving customer from database for id {} {}", customerId, ObjectFormat.jsonAsString(customerResponseDto));
        log.info("CustomerService:getCustomerById execution ended.");
        return customerResponseDto;
    }

    @Override
    public CustomerResponseDto createNewCustomer(CustomerRequestDto customerRequestDto) {
        return processCustomer(customerRequestDto, "createNewCustomer");
    }

    @Override
    public CustomerResponseDto updateCustomer(CustomerRequestDto customerRequestDto) {
        return processCustomer(customerRequestDto, "updateCustomer");
    }

    private CustomerResponseDto processCustomer(CustomerRequestDto customerRequestDto, String customerFunctionName) {
        CustomerResponseDto customerResponseDto;

        log.info(String.format("CustomerService:%s execution started.", customerFunctionName));
        Customer customer = customerMapper.dtoToModule(customerRequestDto);
        log.debug(String.format("CustomerService:%s request parameters {}", customerFunctionName), ObjectFormat.jsonAsString(customerRequestDto));

            /*
            To ensure the customer's existence, we perform a verification step when creating a customer.
            This verification is not necessary when updating a customer since we already know it exists.
             */
        if (customerFunctionName.equals("createNewCustomer") && customerDao.existsById(customerRequestDto.getId()))
            throw new CustomerAlreadyExistsException(String.format("The customer with id %d is already exists.", customerRequestDto.getId()));
        Customer customerResults = customerDao.save(customer);
        customerResponseDto = customerMapper.modelToDto(customerResults);
        log.debug(String.format("CustomerService:%s received response from Database {}", customerFunctionName), ObjectFormat.jsonAsString(customerRequestDto));

        log.info(String.format("CustomerService:%s execution ended.", customerFunctionName));
        return customerResponseDto;
    }

    @Override
    public void deteteCustomerById(Long customerId) {
        log.info("CustomerService:deleteCustomer execution started.");
        customerDao.deleteById(customerId);
        log.debug("CustomerService:deleteCustomer customer is deleted from the Database");

        log.info("CustomerService:deleteCustomer execution ended.");
    }
}
