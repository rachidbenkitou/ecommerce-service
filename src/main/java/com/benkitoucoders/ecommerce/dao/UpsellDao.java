package com.benkitoucoders.ecommerce.dao;


import com.benkitoucoders.ecommerce.entities.Upsell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UpsellDao extends JpaRepository<Upsell, Long>
{
}
