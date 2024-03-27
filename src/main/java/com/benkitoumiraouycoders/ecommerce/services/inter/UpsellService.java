package com.benkitoumiraouycoders.ecommerce.services.inter;

import com.benkitoumiraouycoders.ecommerce.dtos.UpsellDto;
import com.benkitoumiraouycoders.ecommerce.entities.Upsell;
import com.benkitoumiraouycoders.ecommerce.dtos.ResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;


public interface UpsellService {
    Page<Upsell> getUpsells(Pageable pageable);

    UpsellDto getUpsellById(Long id);

    UpsellDto addUpsell(UpsellDto upsellDto) throws IOException;

    UpsellDto updateUpsell(Long id, UpsellDto upsellDto);

    ResponseDto deleteUpsellById(Long id);
}
