package com.benkitoumiraouycoders.ecommerce.controllers;


import com.benkitoumiraouycoders.ecommerce.dao.UpsellDao;
import com.benkitoumiraouycoders.ecommerce.entities.Upsell;
import com.benkitoumiraouycoders.ecommerce.services.UpsellService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UpsellController {

    private final UpsellService upsellService;

    @GetMapping("/upsell/all")
    public List<Upsell> getUpsell()
    {
        return upsellService.getUpsell();
    }


}
