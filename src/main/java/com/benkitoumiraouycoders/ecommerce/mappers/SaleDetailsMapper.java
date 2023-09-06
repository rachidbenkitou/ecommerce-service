package com.benkitoumiraouycoders.ecommerce.mappers;

import com.benkitoumiraouycoders.ecommerce.dtos.SaleDetailsDto;
import com.benkitoumiraouycoders.ecommerce.dtos.SaleDto;
import com.benkitoumiraouycoders.ecommerce.entities.Sale;
import com.benkitoumiraouycoders.ecommerce.entities.SaleDetails;
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
