package com.benkitoucoders.ecommerce.mappers;

import com.benkitoucoders.ecommerce.entities.SaleDetails;
import com.benkitoucoders.ecommerce.dtos.SaleDetailsDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
@Component
public interface SaleDetailsMapper {
    SaleDetailsDto modelToDto(SaleDetails saleDetails);

    List<SaleDetailsDto> modelsToDtos(List<SaleDetails> saleDetailsList);

    SaleDetails dtoToModel(SaleDetailsDto saleDetailsDto);
}
