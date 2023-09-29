package com.benkitoumiraouycoders.ecommerce.services.inter;

import com.benkitoumiraouycoders.ecommerce.dtos.ClientOrderDto;
import com.benkitoumiraouycoders.ecommerce.enums.OrderStatus;
import com.benkitoumiraouycoders.ecommerce.handlers.ResponseDto;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface ClientOrderService {
    List<ClientOrderDto> getClientOrdersByQuery(Long orderId, Long clientId, String orderStatus, LocalDateTime dateCreation, LocalDateTime dateUpdate);

    ClientOrderDto getClientOrderById(Long id);

    ClientOrderDto addClientOrder(ClientOrderDto clientOrderDto) throws IOException;

    ClientOrderDto updateClientOrder(Long id, ClientOrderDto clientOrderDto);

    ResponseDto deleteClientOrderById(Long id);
}
