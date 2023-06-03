package com.benkitoucoders.orderservice.daos;

import com.benkitoucoders.orderservice.entities.GuestOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestOrderDao extends JpaRepository<GuestOrder, Long> {
}
