package com.benkitoucoders.orderservice.entities;

import com.benkitoucoders.orderservice.entities.superEntities.ProductOrder;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@Entity
public class ProductCustomerOrder extends ProductOrder implements Serializable {
    @Id
    private Long id;
}
