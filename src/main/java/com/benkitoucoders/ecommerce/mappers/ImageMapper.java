package com.benkitoucoders.ecommerce.mappers;

import com.benkitoucoders.ecommerce.dtos.ImageDto;
import com.benkitoucoders.ecommerce.entities.Image;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
@Component
public interface ImageMapper {
    ImageDto modelToDto(Image image);

    List<ImageDto> modelsToDtos(List<Image> imageList);

    Image dtoToModel(ImageDto imageDto);
}
