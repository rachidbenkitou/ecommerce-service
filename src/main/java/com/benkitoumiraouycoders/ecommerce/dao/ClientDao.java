package com.benkitoumiraouycoders.ecommerce.dao;

import com.benkitoumiraouycoders.ecommerce.dtos.ClientDto;
import com.benkitoumiraouycoders.ecommerce.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientDao extends JpaRepository<Client, Long>, JpaSpecificationExecutor<Client> {
    @Query("SELECT NEW com.benkitoumiraouycoders.ecommerce.dtos.ClientDto(c.id, c.firstName, c.lastName, c.address," +
            "c.statusId, cs.name, c.email, c.phoneNumber) " +
            "FROM Client c " +
            "LEFT JOIN  ClientStatus cs ON c.statusId = cs.id " +
            "WHERE (:clientId IS NULL OR c.id = :clientId) " +
            "AND (:statusId IS NULL OR c.statusId = :statusId) " +
            "AND (:firstName IS NULL OR LOWER(c.firstName) LIKE LOWER(CONCAT('%', :firstName, '%')))" +
            "AND (:email IS NULL OR LOWER(c.email) LIKE LOWER(CONCAT('%', :email, '%')))" +
            "AND (:phoneNumber IS NULL OR LOWER(c.phoneNumber) LIKE LOWER(CONCAT('%', :phoneNumber, '%')))" +
            "AND (:lastName IS NULL OR LOWER(c.lastName) LIKE LOWER(CONCAT('%', :lastName, '%')))")
    List<ClientDto> findAllClients(@Param("clientId") Long clientId,
                                   @Param("firstName") String firstName,
                                   @Param("lastName") String lastName,
                                   @Param("email") String email,
                                   @Param("phoneNumber") String phoneNumber,
                                   @Param("statusId") Long statusId
    );

    boolean existsByEmailAndPhoneNumber(String email, String phoneNumber);
}
