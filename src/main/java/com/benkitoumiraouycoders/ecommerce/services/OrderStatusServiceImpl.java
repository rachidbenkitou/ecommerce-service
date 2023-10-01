package com.benkitoumiraouycoders.ecommerce.services;

import com.benkitoumiraouycoders.ecommerce.dao.OrderStatusDao;
import com.benkitoumiraouycoders.ecommerce.dtos.OrderStatusDto;
import com.benkitoumiraouycoders.ecommerce.mappers.OrderStatusMapper;
import com.benkitoumiraouycoders.ecommerce.services.inter.OrderStatusService;
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
