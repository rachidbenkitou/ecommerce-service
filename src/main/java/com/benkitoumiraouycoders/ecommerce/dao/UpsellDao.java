package com.benkitoumiraouycoders.ecommerce.dao;


import com.benkitoumiraouycoders.ecommerce.entities.Upsell;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UpsellDao extends JpaRepository<Upsell, Long>
{
}
