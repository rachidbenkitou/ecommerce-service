package com.benkitoucoders.ecommerce.services;

import com.benkitoucoders.ecommerce.services.inter.OrderStatusService;
import com.benkitoucoders.ecommerce.dao.OrderStatusDao;
import com.benkitoucoders.ecommerce.dtos.OrderStatusDto;
import com.benkitoucoders.ecommerce.mappers.OrderStatusMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderStatusServiceImpl implements OrderStatusService {
    private final OrderStatusDao orderStatusDao;
    private final OrderStatusMapper orderStatusMapper;

    @Override
    public List<OrderStatusDto> getOrderStatus() {
        return orderStatusMapper.modelsToDtos(orderStatusDao.findAll());
    }
}
