package com.benkitoumiraouycoders.ecommerce.mappers;

import com.benkitoumiraouycoders.ecommerce.dtos.ClientOrderDetailsDto;
import com.benkitoumiraouycoders.ecommerce.entities.ClientOrderDetails;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
@Component
public interface ClientOrderDetailsMapper {
    ClientOrderDetailsDto modelToDto(ClientOrderDetails clientOrderDetails);

    List<ClientOrderDetailsDto> modelsToDtos(List<ClientOrderDetails> clientOrderDetailsList);

    ClientOrderDetails dtoToModel(ClientOrderDetailsDto clientOrderDetailsDto);
}
