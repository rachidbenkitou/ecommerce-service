package com.benkitoumiraouycoders.ecommerce.services.inter;

import com.benkitoumiraouycoders.ecommerce.dtos.ClientStatusDto;
import com.benkitoumiraouycoders.ecommerce.dtos.OrderStatusDto;

import java.util.List;

public interface ClientStatusService {
    List<ClientStatusDto> getClientStatus();

}
