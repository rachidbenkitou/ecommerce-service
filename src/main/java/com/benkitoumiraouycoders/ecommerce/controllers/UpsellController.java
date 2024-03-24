package com.benkitoumiraouycoders.ecommerce.controllers;


import com.benkitoumiraouycoders.ecommerce.dtos.UpsellDto;
import com.benkitoumiraouycoders.ecommerce.entities.Upsell;
import com.benkitoumiraouycoders.ecommerce.services.UpsellServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/upsell")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:58213", allowCredentials = "true")
public class UpsellController {

    private final UpsellServiceImpl upsellService;
    @GetMapping
    public Page<Upsell> getAllUpsells(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return upsellService.getUpsells(PageRequest.of(page, size));
    }

    @GetMapping("/{upsellId}")
    public ResponseEntity<UpsellDto> getUpsellById(@PathVariable Long upsellId) {
        return ResponseEntity.ok().body(upsellService.getUpsellById(upsellId));
    }

    @PostMapping
    public ResponseEntity<UpsellDto> addUpsell(
            @RequestBody UpsellDto upsellDto) throws IOException {
        return ResponseEntity.ok().body(upsellService.addUpsell(upsellDto));
    }


    @PutMapping("/{upsellId}")
    public ResponseEntity<UpsellDto> updateUpsell
            (@PathVariable Long upsellId, @RequestBody UpsellDto upsellDto) {
        return ResponseEntity.ok().body(upsellService.updateUpsell(upsellId, upsellDto));
    }

    @DeleteMapping("/{upsellId}")
    public ResponseEntity<?> deleteClientById(@PathVariable Long upsellId) {
        return ResponseEntity.ok().body(upsellService.deleteUpsellById(upsellId));
    }


}
