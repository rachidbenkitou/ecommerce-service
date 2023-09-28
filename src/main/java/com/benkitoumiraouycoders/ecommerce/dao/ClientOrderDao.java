package com.benkitoumiraouycoders.ecommerce.dao;

import com.benkitoumiraouycoders.ecommerce.dtos.ClientOrderDto;
import com.benkitoumiraouycoders.ecommerce.entities.ClientOrder;
import com.benkitoumiraouycoders.ecommerce.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ClientOrderDao extends JpaRepository<ClientOrder, Long>, JpaSpecificationExecutor<ClientOrder> {
    @Query("SELECT NEW com.benkitoumiraouycoders.ecommerce.dtos.ClientOrderDto(c.id, c.totalPrice, c.orderStatus, c.clientId," +
            "cs.firstName, cs.lastName, cs.email, cs.phoneNumber, c.description, c.dateCreation, c.dateUpdate) " +
            "FROM ClientOrder c " +
            "LEFT JOIN  Client cs ON c.clientId = cs.id " +
            "WHERE (:orderId IS NULL OR c.id = :orderId) " +
            "AND (:orderStatus IS NULL OR c.orderStatus = :orderStatus) " +
            "AND (:clientId IS NULL OR c.clientId = :clientId) " +
            "AND (:dateCreation IS NULL OR c.dateCreation = :dateCreation) " +
            "AND (:dateUpdate IS NULL OR c.dateUpdate = :dateUpdate) ")
    List<ClientOrderDto> findAllClientOrders(
            @Param("orderId") Long orderId,
            @Param("clientId") Long clientId,
            @Param("dateCreation") LocalDateTime dateCreation,
            @Param("dateUpdate") LocalDateTime dateUpdate,
            @Param("orderStatus") OrderStatus orderStatus
    );
}
