package com.benkitoumiraouycoders.ecommerce.dao;

import com.benkitoumiraouycoders.ecommerce.entities.ClientStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientStatusDao extends JpaRepository<ClientStatus, Long> {
}
