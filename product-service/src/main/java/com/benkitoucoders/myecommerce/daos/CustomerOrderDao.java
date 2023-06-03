package com.benkitoucoders.myecommerce.daos;

import com.benkitoucoders.myecommerce.entities.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerOrderDao extends JpaRepository<CustomerOrder, Long> {
}
