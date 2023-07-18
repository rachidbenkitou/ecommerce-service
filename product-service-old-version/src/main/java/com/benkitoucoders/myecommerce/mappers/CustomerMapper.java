package com.benkitoucoders.myecommerce.mappers;

import com.benkitoucoders.myecommerce.dtos.customer.CustomerRequestDto;
import com.benkitoucoders.myecommerce.dtos.customer.CustomerResponseDto;
import com.benkitoucoders.myecommerce.entities.Customer;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
@Service
public interface CustomerMapper {
    CustomerResponseDto modelToDto(Customer customer);
    Customer dtoToModule(CustomerRequestDto customerRequestDto);
}
