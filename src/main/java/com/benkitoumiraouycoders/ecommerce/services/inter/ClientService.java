package com.benkitoumiraouycoders.ecommerce.services.inter;

import com.benkitoumiraouycoders.ecommerce.dtos.ClientDto;
import com.benkitoumiraouycoders.ecommerce.dtos.ResponseDto;

import java.io.IOException;
import java.util.List;

public interface ClientService {
    List<ClientDto> getClientsByQuery(Long clientId, String firstName, String lastName, String email, String phoneNumber, Long statusId);

    ClientDto getClientById(Long id);

    ClientDto addClient(ClientDto clientDto) throws IOException;

    ClientDto updateClient(Long id, ClientDto clientDto);

    ResponseDto deleteClientById(Long id);
}
