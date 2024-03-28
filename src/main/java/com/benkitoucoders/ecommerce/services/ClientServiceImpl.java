package com.benkitoucoders.ecommerce.services;

import com.benkitoucoders.ecommerce.exceptions.EntityAlreadyExistsException;
import com.benkitoucoders.ecommerce.exceptions.EntityNotFoundException;
import com.benkitoucoders.ecommerce.services.inter.ClientService;
import com.benkitoucoders.ecommerce.dao.ClientDao;
import com.benkitoucoders.ecommerce.dtos.ClientDto;
import com.benkitoucoders.ecommerce.entities.Client;
import com.benkitoucoders.ecommerce.dtos.ResponseDto;
import com.benkitoucoders.ecommerce.mappers.ClientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientDao clientDao;
    private final ClientMapper clientMapper;

    @Override
    public List<ClientDto> getClientsByQuery(Long clientId, String firstName, String lastName,
                                             String email, String phoneNumber, Long statusId) {
        return clientDao.findAllClients(clientId, firstName, lastName, email, phoneNumber, statusId);
    }

    @Override
    public ClientDto getClientById(Long id) {
        List<ClientDto> clientDtoList = clientDao.findAllClients(id, null, null, null, null, null);
        return clientDtoList.stream()
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException(String.format("The client with the id %d is not found.", id)));
    }

    @Override
    public ClientDto addClient(ClientDto clientDto) throws IOException {
        if (clientDao.existsByEmailAndPhoneNumber(clientDto.getEmail(), clientDto.getPhoneNumber())) {
            throw new EntityAlreadyExistsException(String.format("The client with the email %s and phone number is already exists", clientDto.getEmail(), clientDto.getPhoneNumber()));
        }
        clientDto.setId(null);

        return clientMapper.modelToDto(clientDao.save(clientMapper.dtoToModel(clientDto)));
    }

    @Override
    public ClientDto updateClient(Long id, ClientDto clientDto) {
        ClientDto oldClientDto = getClientById(id);
        clientDto.setId(oldClientDto.getId());
        Client updatedClient = clientDao.save(clientMapper.dtoToModel(clientDto));
        return clientMapper.modelToDto(updatedClient);
    }

    @Override
    public ResponseDto deleteClientById(Long id) {
        getClientById(id);
        clientDao.deleteById(id);
        return ResponseDto.builder()
                .message("Client successfully deleted.")
                .build();
    }
}
