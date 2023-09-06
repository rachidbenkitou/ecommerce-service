package com.benkitoumiraouycoders.ecommerce.mappers;

import com.benkitoumiraouycoders.ecommerce.dtos.ImageDto;
import com.benkitoumiraouycoders.ecommerce.dtos.SaleDto;
import com.benkitoumiraouycoders.ecommerce.entities.Image;
import com.benkitoumiraouycoders.ecommerce.entities.Sale;
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
