package com.benkitoumiraouycoders.ecommerce.services.inter;

import com.benkitoumiraouycoders.ecommerce.entities.Upsell;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UpsellService {
    Page<Upsell> getUpsells(Pageable pageable);
}
