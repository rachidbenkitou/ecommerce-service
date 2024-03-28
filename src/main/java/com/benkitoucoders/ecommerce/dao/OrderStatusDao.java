package com.benkitoucoders.ecommerce.dao;

import com.benkitoucoders.ecommerce.entities.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusDao extends JpaRepository<OrderStatus, Long> {
}
