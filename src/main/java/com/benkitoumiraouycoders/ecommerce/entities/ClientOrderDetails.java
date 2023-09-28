package com.benkitoumiraouycoders.ecommerce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ClientOrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long clientOrderId;
    private Long productId;
    private Double price;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "CLIENT_ORDER_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private ClientOrder clientOrder;
}
