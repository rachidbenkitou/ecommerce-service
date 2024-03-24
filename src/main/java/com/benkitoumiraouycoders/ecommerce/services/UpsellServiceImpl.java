package com.benkitoumiraouycoders.ecommerce.services;


import com.benkitoumiraouycoders.ecommerce.dao.UpsellDao;
import com.benkitoumiraouycoders.ecommerce.entities.Upsell;
import com.benkitoumiraouycoders.ecommerce.services.inter.UpsellService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UpsellServiceImpl implements UpsellService {

    private final UpsellDao upsellDao;

    @Override
    public Page<Upsell> getUpsells(Pageable pageable) {
        return upsellDao.findAll(pageable);
    }
}
