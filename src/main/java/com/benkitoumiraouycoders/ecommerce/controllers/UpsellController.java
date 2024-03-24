package com.benkitoumiraouycoders.ecommerce.controllers;


import com.benkitoumiraouycoders.ecommerce.entities.Upsell;
import com.benkitoumiraouycoders.ecommerce.services.UpsellServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UpsellController {

    private final UpsellServiceImpl upsellService;

    @GetMapping("/upsell/all")
    public List<Upsell> getUpsell()
    {
        return upsellService.getUpsell();
    }


    @GetMapping("/upsells")
    public Page<Upsell> listUpsells(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int siez)
    {
        return upsellService.getUpsell(PageRequest.of(page,siez));
    };

}
