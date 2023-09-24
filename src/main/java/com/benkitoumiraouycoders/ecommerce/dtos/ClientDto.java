package com.benkitoumiraouycoders.ecommerce.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ClientDto implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phoneNumber;
    private Long statusId;
    private String statusName;

    ClientDto(Long id, String firstName, String lastName,
              String address, Long statusId, String statusName, String email, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.statusId = statusId;
        this.statusName = statusName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
