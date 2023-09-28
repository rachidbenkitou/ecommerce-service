package com.benkitoumiraouycoders.ecommerce.dtos;

import com.benkitoumiraouycoders.ecommerce.enums.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ClientOrderDto implements Serializable {
    private Long id;
    private String description;
    private LocalDateTime dateCreation;
    private LocalDateTime dateUpdate;
    private OrderStatus orderStatus;
    private Double totalPrice;
    private Long clientId;
    private String clientFirstName;
    private String clientLastName;
    private String clientEmail;
    private String clientPhoneNumber;

    public ClientOrderDto(Long id, Double totalPrice, OrderStatus orderStatus, Long clientId, String clientFirstName, String clientLastName,
                          String clientEmail, String clientPhoneNumber, String description,
                          LocalDateTime dateCreation, LocalDateTime dateUpdate) {

        this.id = id;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
        this.clientId = clientId;
        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
        this.clientEmail = clientEmail;
        this.clientPhoneNumber = clientPhoneNumber;
        this.description = description;
        this.dateCreation = dateCreation;
        this.dateUpdate = dateUpdate;

    }
}
