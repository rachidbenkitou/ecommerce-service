package com.benkitoumiraouycoders.ecommerce.controllers;


import com.benkitoumiraouycoders.ecommerce.entities.Upsell;
import com.benkitoumiraouycoders.ecommerce.services.UpsellServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UpsellController {

    private final UpsellServiceImpl upsellService;
    @GetMapping
    public Page<Upsell> getAllUpsells(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return upsellService.getUpsells(PageRequest.of(page, size));
    }
}
