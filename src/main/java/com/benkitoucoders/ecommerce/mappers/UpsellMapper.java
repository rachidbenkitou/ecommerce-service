package com.benkitoucoders.ecommerce.mappers;


import com.benkitoucoders.ecommerce.dtos.UpsellDto;
import com.benkitoucoders.ecommerce.entities.Upsell;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
@Component
public interface UpsellMapper {

    UpsellDto modelToDto(Upsell upsell);

    List<Upsell> modelsToDtos(List<Upsell> upsellList);

    Upsell dtoToModel(UpsellDto UpsellDto);

}
