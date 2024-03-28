package com.benkitoucoders.ecommerce.dao;

import com.benkitoucoders.ecommerce.dtos.SaleDto;
import com.benkitoucoders.ecommerce.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleDao extends JpaRepository<Sale, Long>, JpaSpecificationExecutor<Sale> {
    @Query(value = "select new com.benkitoucoders.ecommerce.dtos.SaleDto(" +
            " sale.id,sale.address, sale.email, sale.phone, sale.totalPrice," +
            " sale.saleStatus,sale.dateCreation, sale.dateUpdate, os.id, os.name, os.color) " +
            " FROM Sale sale " +
            "LEFT JOIN  OrderStatus os ON sale.saleStatusId = os.id " +
            " WHERE (:id IS NULL OR sale.id = :id) AND (:saleStatusId IS NULL OR sale.saleStatusId = :saleStatusId)  ")
    List<SaleDto> getSalesByQuery(
            @Param("id") Long id,
            @Param("saleStatusId") Long saleStatusId
    );
}
