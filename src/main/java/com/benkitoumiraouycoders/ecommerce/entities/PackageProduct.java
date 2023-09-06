package com.benkitoumiraouycoders.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
public class PackageProduct implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "PACKAGE_ID")
    private Long packageId;

    @Column(name = "PRODUCT_ID")
    private Long productId;
    @Column(name = "PRODUCT_QUANTITY")
    private Long productQuantity;
    @Column(name = "DATE_CREATION")
    private LocalDateTime dateCreation;

    @Column(name = "USER_CREATION")
    private Long userCreation;

    @Column(name = "DATE_UPDATE")
    private LocalDateTime dateUpdate;

    @Column(name = "USER_UPDATE")
    private Long userUpdate;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "PACKAGE_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private Package aPackage;

}
