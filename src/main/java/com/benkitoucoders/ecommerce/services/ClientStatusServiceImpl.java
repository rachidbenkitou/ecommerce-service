package com.benkitoucoders.ecommerce.services;

import com.benkitoucoders.ecommerce.services.inter.ClientStatusService;
import com.benkitoucoders.ecommerce.dao.ClientStatusDao;
import com.benkitoucoders.ecommerce.dtos.ClientStatusDto;
import com.benkitoucoders.ecommerce.mappers.ClientStatusMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientStatusServiceImpl implements ClientStatusService {
    private final ClientStatusDao clientStatusDao;
    private final ClientStatusMapper clientStatusMapper;

    @Override
    public List<ClientStatusDto> getClientStatus() {
        return clientStatusMapper.modelsToDtos(clientStatusDao.findAll());
    }
}
