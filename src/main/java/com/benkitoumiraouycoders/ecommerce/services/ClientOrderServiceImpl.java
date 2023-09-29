package com.benkitoumiraouycoders.ecommerce.services;

import com.benkitoumiraouycoders.ecommerce.dao.ClientOrderDao;
import com.benkitoumiraouycoders.ecommerce.dtos.ClientOrderDto;
import com.benkitoumiraouycoders.ecommerce.entities.ClientOrder;
import com.benkitoumiraouycoders.ecommerce.enums.OrderStatus;
import com.benkitoumiraouycoders.ecommerce.exceptions.EntityNotFoundException;
import com.benkitoumiraouycoders.ecommerce.handlers.ResponseDto;
import com.benkitoumiraouycoders.ecommerce.mappers.ClientOrderMapper;
import com.benkitoumiraouycoders.ecommerce.services.inter.ClientOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientOrderServiceImpl implements ClientOrderService {
    private final ClientOrderDao clientOrderDao;
    private final ClientOrderMapper clientOrderMapper;

    @Override
    public List<ClientOrderDto> getClientOrdersByQuery(Long orderId, Long clientId, String orderStatus, LocalDateTime dateCreation, LocalDateTime dateUpdate) {
        return clientOrderDao.findAllClientOrders(orderId, clientId, dateCreation, dateUpdate, orderStatus);
    }

    @Override
    public ClientOrderDto getClientOrderById(Long id) {
        List<ClientOrderDto> clientOrderDtoList = clientOrderDao.findAllClientOrders(id, null, null, null, null);
        return clientOrderDtoList.stream()
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException(String.format("The clientOrder with the id %d is not found.", id)));
    }

    @Override
    public ClientOrderDto addClientOrder(ClientOrderDto clientOrderDto) throws IOException {
        clientOrderDto.setId(null);
        return clientOrderMapper.modelToDto(clientOrderDao.save(clientOrderMapper.dtoToModel(clientOrderDto)));
    }

    @Override
    public ClientOrderDto updateClientOrder(Long id, ClientOrderDto clientOrderDto) {
        ClientOrderDto oldClientOrderDto = getClientOrderById(id);
        clientOrderDto.setId(oldClientOrderDto.getId());
        ClientOrder updatedClientOrder = clientOrderDao.save(clientOrderMapper.dtoToModel(clientOrderDto));
        return clientOrderMapper.modelToDto(updatedClientOrder);
    }

    @Override
    public ResponseDto deleteClientOrderById(Long id) {
        getClientOrderById(id);
        clientOrderDao.deleteById(id);
        return ResponseDto.builder()
                .message("ClientOrder successfully deleted.")
                .build();
    }
}
