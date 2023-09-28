package com.benkitoumiraouycoders.ecommerce.dao;

import com.benkitoumiraouycoders.ecommerce.dtos.SaleDto;
import com.benkitoumiraouycoders.ecommerce.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleDao extends JpaRepository<Sale, Long>, JpaSpecificationExecutor<Sale> {
    @Query(value = "select new com.benkitoumiraouycoders.ecommerce.dtos.SaleDto(" +
            " sale.id,sale.address, sale.email, sale.phone, sale.totalPrice," +
            " sale.saleStatus,sale.dateCreation, sale.dateUpdate) " +
            " FROM Sale sale " +
            " WHERE (:id IS NULL OR sale.id = :id) ")
    List<SaleDto> getSalesByQuery(
            @Param("id") Long id
    );
}
