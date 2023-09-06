package com.benkitoumiraouycoders.ecommerce.mappers;

import com.benkitoumiraouycoders.ecommerce.dtos.ImageDto;
import com.benkitoumiraouycoders.ecommerce.dtos.PackageDto;
import com.benkitoumiraouycoders.ecommerce.entities.Image;
import com.benkitoumiraouycoders.ecommerce.entities.Package;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
@Component
public interface PackageMapper {
    PackageDto modelToDto(Package source);

    List<PackageDto> modelsToDtos(List<Package> sourceList);

    Package dtoToModel(PackageDto imageDto);
}
