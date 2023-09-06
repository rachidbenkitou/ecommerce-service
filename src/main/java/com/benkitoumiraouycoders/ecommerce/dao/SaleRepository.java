package com.benkitoumiraouycoders.ecommerce.dao;

import com.benkitoumiraouycoders.ecommerce.dtos.ProductDto;
import com.benkitoumiraouycoders.ecommerce.dtos.SaleDto;
import com.benkitoumiraouycoders.ecommerce.entities.Product;
import com.benkitoumiraouycoders.ecommerce.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long>, JpaSpecificationExecutor<Sale> {
    @Query(value = "select new com.benkitoumiraouycoders.ecommerce.dtos.SaleDto(" +
            " sale.id,sale.dateCreation, sale.userUpdate, sale.dateUpdate, sale.userCreation" +
            " ,sale.address, sale.cni, sale.email,sale.phone, sale.saleStatus, sale.isPayed) " +
            " FROM Sale sale " +
            " WHERE (:id IS NULL OR sale.id = :id) ")

    List<SaleDto> getSaleByQuery(
            @Param("id") Long id
    );
}
