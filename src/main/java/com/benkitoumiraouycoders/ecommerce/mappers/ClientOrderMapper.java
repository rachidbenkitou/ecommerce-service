package com.benkitoumiraouycoders.ecommerce.mappers;

import com.benkitoumiraouycoders.ecommerce.dtos.ClientOrderDto;
import com.benkitoumiraouycoders.ecommerce.entities.ClientOrder;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
@Component
public interface ClientOrderMapper {
    ClientOrderDto modelToDto(ClientOrder clientOrder);

    List<ClientOrderDto> modelsToDtos(List<ClientOrder> clientOrderList);

    ClientOrder dtoToModel(ClientOrderDto clientOrderDto);
}
