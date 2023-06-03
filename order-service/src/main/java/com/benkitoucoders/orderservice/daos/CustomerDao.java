package com.benkitoucoders.orderservice.daos;

import com.benkitoucoders.orderservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Long> {
}
