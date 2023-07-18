package com.benkitoucoders.myecommerce.daos;

import com.benkitoucoders.myecommerce.entities.order.GuestOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestOrderDao extends JpaRepository<GuestOrder, Long> {
}
