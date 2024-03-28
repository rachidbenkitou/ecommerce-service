package com.benkitoucoders.ecommerce.services.inter;

import com.benkitoucoders.ecommerce.dtos.ClientStatusDto;

import java.util.List;

public interface ClientStatusService {
    List<ClientStatusDto> getClientStatus();

}
