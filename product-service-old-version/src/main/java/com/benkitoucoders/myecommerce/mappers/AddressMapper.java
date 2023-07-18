package com.benkitoucoders.myecommerce.mappers;

import com.benkitoucoders.myecommerce.dtos.address.AddressRequestDto;
import com.benkitoucoders.myecommerce.dtos.address.AddressResponseDto;
import com.benkitoucoders.myecommerce.entities.Address;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
@Service
public interface AddressMapper {
    AddressResponseDto modelToDto(Address address);
    Address dtoToModule(AddressRequestDto addressRequestDto);
}
