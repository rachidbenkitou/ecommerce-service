package com.benkitoumiraouycoders.ecommerce.dao;


import com.benkitoumiraouycoders.ecommerce.dtos.SaleDetailsDto;
import com.benkitoumiraouycoders.ecommerce.entities.Product;
import com.benkitoumiraouycoders.ecommerce.entities.SaleDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleDetailsRepository extends JpaRepository<SaleDetails, Long>, JpaSpecificationExecutor<SaleDetails> {
    @Query(value = "select new com.benkitoumiraouycoders.ecommerce.dtos.SaleDetailsDto(" +
            " saleDetails.id,saleDetails.dateCreation, saleDetails.userUpdate, saleDetails.dateUpdate, saleDetails.userCreation" +
            " ,saleDetails.packageId, saleDetails.productId, saleDetails.packageQuantity,saleDetails.productQuantity, saleDetails.saleId) " +
            " FROM SaleDetails saleDetails " +
            " WHERE (:id IS NULL OR saleDetails.id = :id) ")

    List<SaleDetailsDto> getsaleDetailsByQuery(
            @Param("id") Long id
    );
}
