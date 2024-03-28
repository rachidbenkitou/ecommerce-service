package com.benkitoucoders.ecommerce.services.inter;

import com.benkitoucoders.ecommerce.dtos.ClientOrderDetailsDto;
import com.benkitoucoders.ecommerce.dtos.ResponseDto;

import java.io.IOException;
import java.util.List;

public interface ClientOrderDetailsService {
    List<ClientOrderDetailsDto> getClientOrderDetailsByQuery(Long orderId);

    ClientOrderDetailsDto addClientOrderDetails(ClientOrderDetailsDto clientOrderDetailsDto) throws IOException;

    ResponseDto deleteClientOrderDetailsById(Long id);
}
