package com.benkitoumiraouycoders.ecommerce.controllers;

import com.benkitoumiraouycoders.ecommerce.criteria.SaleDetailsCriteria;
import com.benkitoumiraouycoders.ecommerce.dtos.SaleDetailsDto;
import com.benkitoumiraouycoders.ecommerce.services.inter.SaleDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/saleDetails")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:58213", allowCredentials = "true")
public class SaleDetailsController {

    private SaleDetailsService saleDetailsService;

    @GetMapping
    public ResponseEntity<List<SaleDetailsDto>> getSaleDetailsByQuery(
            @RequestParam(name = "saleId", required = false) Long saleId
    ) {
        SaleDetailsCriteria saleDetailsCriteria = SaleDetailsCriteria.builder()
                .id(saleId)
                .build();
        return ResponseEntity.ok().body(saleDetailsService.findSaleDetailsByCriteria(saleDetailsCriteria));
    }

    @PostMapping
    public ResponseEntity<SaleDetailsDto> addSaleDetails(
            @RequestBody SaleDetailsDto saleDetailsDto) throws IOException {
        return ResponseEntity.ok().body(saleDetailsService.persistSaleDetails(saleDetailsDto));
    }

    @DeleteMapping("/{saleDetailsId}")
    public ResponseEntity<?> deleteSaleDetailsById(@PathVariable Long saleDetailsId) {
        return ResponseEntity.ok().body(saleDetailsService.deleteSaleDetailsById(saleDetailsId));
    }

}
