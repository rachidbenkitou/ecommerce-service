package com.benkitoucoders.ecommerce.mappers;

import com.benkitoucoders.ecommerce.dtos.SupplierProductDto;
import com.benkitoucoders.ecommerce.entities.SupplierProduct;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
@Component
public interface SupplierProductMapper {
    SupplierProductDto modelToDto(SupplierProduct supplierProduct);

    List<SupplierProductDto> modelsToDtos(List<SupplierProduct> supplierProductList);

    SupplierProduct dtoToModel(SupplierProductDto supplierProductDto);
}
