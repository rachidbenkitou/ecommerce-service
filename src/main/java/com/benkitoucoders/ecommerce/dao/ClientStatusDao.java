package com.benkitoucoders.ecommerce.dao;

import com.benkitoucoders.ecommerce.entities.ClientStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientStatusDao extends JpaRepository<ClientStatus, Long> {
}
