package com.benkitoucoders.orderservice.daos;

import com.benkitoucoders.orderservice.entities.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerOrderDao extends JpaRepository<CustomerOrder, Long> {
}
