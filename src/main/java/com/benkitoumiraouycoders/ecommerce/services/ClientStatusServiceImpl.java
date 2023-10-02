package com.benkitoumiraouycoders.ecommerce.services;

import com.benkitoumiraouycoders.ecommerce.dao.ClientStatusDao;
import com.benkitoumiraouycoders.ecommerce.dtos.ClientStatusDto;
import com.benkitoumiraouycoders.ecommerce.mappers.ClientStatusMapper;
import com.benkitoumiraouycoders.ecommerce.services.inter.ClientStatusService;
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
