package com.benkitoumiraouycoders.ecommerce.controllers;

import com.benkitoumiraouycoders.ecommerce.Criteria.SaleCriteria;
import com.benkitoumiraouycoders.ecommerce.dtos.SaleDto;
import com.benkitoumiraouycoders.ecommerce.exceptions.EntityNotFoundException;
import com.benkitoumiraouycoders.ecommerce.services.inter.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sale")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:58213", allowCredentials = "true")
public class SaleController {

    private final SaleService saleService;
    
    @GetMapping
    public ResponseEntity<?> findsalesByCriteria(
            @RequestParam(name = "isPayed", required = false) String isPayed,
            @RequestParam(name = "id", required = false) Long id
    ) throws EntityNotFoundException {
          SaleCriteria salesCriteria = new SaleCriteria();
          salesCriteria.setIsPayed(isPayed);
          salesCriteria.setId(id);

        return ResponseEntity.ok(  saleService.findsalesByCriteria( salesCriteria));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findsalesById(@PathVariable Long id) throws EntityNotFoundException {
        return ResponseEntity.ok( saleService.findsalesById(id));
    }

    @PostMapping
    public ResponseEntity<?> persistsales(@RequestBody SaleDto salesDto) throws EntityNotFoundException {
        return ResponseEntity.ok(    saleService.persistsales(salesDto));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updatesales(@PathVariable Long id, @RequestBody SaleDto salesDto) throws EntityNotFoundException {
        return ResponseEntity.ok(saleService.updatesales(id, salesDto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletesalesById(@PathVariable Long id) throws EntityNotFoundException {
        return ResponseEntity.ok(    saleService.deletesalesById(id));
    }
}
