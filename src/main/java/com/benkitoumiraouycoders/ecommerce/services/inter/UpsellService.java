package com.benkitoumiraouycoders.ecommerce.services.inter;

import com.benkitoumiraouycoders.ecommerce.entities.Upsell;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UpsellService {

    List<Upsell> getUpsell();

    Page<Upsell> getUpsell(Pageable pageable);
}
