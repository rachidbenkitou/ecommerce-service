package com.benkitoumiraouycoders.ecommerce.controllers;


import com.benkitoumiraouycoders.ecommerce.dtos.UpsellDto;
import com.benkitoumiraouycoders.ecommerce.entities.Upsell;
import com.benkitoumiraouycoders.ecommerce.services.UpsellServiceImpl;
import com.fasterxml.jackson.core.io.JsonStringEncoder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.models.responses.APIResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/upsell")
@AllArgsConstructor
public class UpsellController {

    private final UpsellServiceImpl upsellService;

    @GetMapping
    public Page<Upsell> getAllUpsells(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        log.info("UpsellController::getAllUpsells Fetching Allupsells  {}.",PageRequest.of(page,size));
        return upsellService.getUpsells(PageRequest.of(page, size));
    }

    @GetMapping("/{upsellId}")
    public ResponseEntity<UpsellDto> getUpsellById(@PathVariable Long upsellId) {
        log.info("UpsellController::getUpsellById Fetching upsell ById with id: {} .", upsellId);
        return ResponseEntity.ok().body(upsellService.getUpsellById(upsellId));
    }

    @PostMapping
    public ResponseEntity<UpsellDto> addUpsell(
            @RequestBody UpsellDto upsellDto) throws IOException {
        log.info("UpsellController::addUpsell response {}", upsellDto);
        return ResponseEntity.ok().body(upsellService.addUpsell(upsellDto));
    }


    @PutMapping("/{upsellId}")
    public ResponseEntity<UpsellDto> updateUpsell
            (@PathVariable Long upsellId, @RequestBody UpsellDto upsellDto) {
        log.info("UpsellController::updateUpsell response ");
        return ResponseEntity.ok().body(upsellService.updateUpsell(upsellId, upsellDto));
    }

    @DeleteMapping("/{upsellId}")
    public ResponseEntity<?> deleteClientById(@PathVariable Long upsellId) {
        log.info("UpsellController::deleteClientById deleteClientById{} ",upsellId);
        return ResponseEntity.ok().body(upsellService.deleteUpsellById(upsellId));
    }
}
