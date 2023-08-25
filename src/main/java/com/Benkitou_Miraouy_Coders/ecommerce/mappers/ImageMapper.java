package com.Benkitou_Miraouy_Coders.ecommerce.mappers;

import com.Benkitou_Miraouy_Coders.ecommerce.entities.Image;
import com.Benkitou_Miraouy_Coders.ecommerce.dtos.ImageDto;
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
