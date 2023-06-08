package com.benkitoucoders.myecommerce.entities.productguestorder;

import com.benkitoucoders.myecommerce.entities.superentities.ProductOrder;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Builder
@EqualsAndHashCode(callSuper = false)
@Entity
public class ProductGuestOrder extends ProductOrder implements Serializable {
    @Id
    private Long id;
}
