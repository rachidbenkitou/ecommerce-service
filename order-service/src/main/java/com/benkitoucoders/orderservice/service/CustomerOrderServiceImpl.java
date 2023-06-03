package com.benkitoucoders.orderservice.service;

import com.benkitoucoders.orderservice.service.interfaces.CustomerOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerOrderServiceImpl implements CustomerOrderService {
}
