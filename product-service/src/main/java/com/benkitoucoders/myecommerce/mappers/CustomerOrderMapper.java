package com.benkitoucoders.myecommerce.mappers;

import com.benkitoucoders.myecommerce.dtos.order.customerorder.CustomerOrderRequestDto;
import com.benkitoucoders.myecommerce.dtos.order.customerorder.CustomerOrderResponseDto;
import com.benkitoucoders.myecommerce.entities.order.CustomerOrder;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
@Service
public interface CustomerOrderMapper {
    CustomerOrderResponseDto modelToDto(CustomerOrder customerOrder);
    CustomerOrder dtoToModule(CustomerOrderRequestDto customerOrderRequestDto);
}
