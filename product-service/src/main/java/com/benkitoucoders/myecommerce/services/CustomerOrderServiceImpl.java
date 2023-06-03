package com.benkitoucoders.myecommerce.services;

import com.benkitoucoders.myecommerce.services.serviceinterfaces.CustomerOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerOrderServiceImpl implements CustomerOrderService {
}
