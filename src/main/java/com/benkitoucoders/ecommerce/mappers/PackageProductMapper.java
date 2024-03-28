package com.benkitoucoders.ecommerce.mappers;

import com.benkitoucoders.ecommerce.entities.PackageProduct;
import com.benkitoucoders.ecommerce.dtos.PackageProductDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
@Component
public interface PackageProductMapper {
    PackageProductDto modelToDto(PackageProduct source);

    List<PackageProductDto> modelsToDtos(List<PackageProduct> sourceList);

    PackageProduct dtoToModel(PackageProductDto imageDto);
}
