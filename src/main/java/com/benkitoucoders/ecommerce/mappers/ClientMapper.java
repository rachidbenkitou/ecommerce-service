package com.benkitoucoders.ecommerce.mappers;

import com.benkitoucoders.ecommerce.entities.Client;
import com.benkitoucoders.ecommerce.dtos.ClientDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
@Component
public interface ClientMapper {
    ClientDto modelToDto(Client client);

    List<ClientDto> modelsToDtos(List<Client> clientList);

    Client dtoToModel(ClientDto clientDto);
}
