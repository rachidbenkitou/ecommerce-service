package com.benkitoucoders.myecommerce.entities;

import com.benkitoucoders.myecommerce.entities.superEntities.ProductOrder;
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
