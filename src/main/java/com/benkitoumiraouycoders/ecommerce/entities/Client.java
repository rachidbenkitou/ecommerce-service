package com.benkitoumiraouycoders.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phoneNumber;
    @Column(name = "STATUS_ID")
    private Long statusId;

    private LocalDateTime dateCreation;
    private Long userCreation;
    @ManyToOne
    @JoinColumn(name = "STATUS_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    private ClientStatus clientStatus;
}
