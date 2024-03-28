package com.benkitoucoders.ecommerce.mappers;

import com.benkitoucoders.ecommerce.entities.Sale;
import com.benkitoucoders.ecommerce.dtos.SaleDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
@Component
public interface SaleMapper {
    SaleDto modelToDto(Sale sale);

    List<SaleDto> modelsToDtos(List<Sale> saleList);

    Sale dtoToModel(SaleDto saleDto);
}
