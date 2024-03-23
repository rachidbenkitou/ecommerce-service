package com.benkitoumiraouycoders.ecommerce.services;


import com.benkitoumiraouycoders.ecommerce.dao.UpsellDao;
import com.benkitoumiraouycoders.ecommerce.entities.Upsell;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UpsellService {

    private final UpsellDao upsellDao;

    public List<Upsell> getUpsell() {return upsellDao.findAll();}


}
