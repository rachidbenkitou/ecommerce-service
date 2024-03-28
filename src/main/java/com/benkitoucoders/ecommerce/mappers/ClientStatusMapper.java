package com.benkitoucoders.ecommerce.mappers;

import com.benkitoucoders.ecommerce.entities.ClientStatus;
import com.benkitoucoders.ecommerce.dtos.ClientStatusDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
@Component
public interface ClientStatusMapper {
    ClientStatusDto modelToDto(ClientStatus clientStatus);

    List<ClientStatusDto> modelsToDtos(List<ClientStatus> clientStatusList);

    ClientStatus dtoToModel(ClientStatusDto clientStatusDto);
}
