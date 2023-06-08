package com.benkitoucoders.myecommerce.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Entity
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String secondName;
    private String phone;
    private Date birthDayDate;
    @OneToOne
    @Column(unique = true)
    private Address address;
    @OneToMany(mappedBy = "customer")
    private List<CustomerOrder> customerOrders;
}
