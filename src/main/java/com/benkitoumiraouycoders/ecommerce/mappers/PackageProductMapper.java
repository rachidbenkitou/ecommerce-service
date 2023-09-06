package com.benkitoumiraouycoders.ecommerce.mappers;

import com.benkitoumiraouycoders.ecommerce.dtos.PackageDto;
import com.benkitoumiraouycoders.ecommerce.dtos.PackageProductDto;
import com.benkitoumiraouycoders.ecommerce.entities.Package;
import com.benkitoumiraouycoders.ecommerce.entities.PackageProduct;
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
