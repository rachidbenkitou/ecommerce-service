package com.benkitoucoders.ecommerce.controllers;

import com.benkitoucoders.ecommerce.dtos.SupplierDto;
import com.benkitoucoders.ecommerce.services.inter.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/suppliers")
@RequiredArgsConstructor
public class SupplierController {
    private final SupplierService supplierService;

    @GetMapping
    public ResponseEntity<List<SupplierDto>> getSuppliersByQuery(@RequestParam(name = "supplierId", required = false) Long supplierId,
                                                                 @RequestParam(name = "name", required = false) String name,
                                                                 @RequestParam(name = "email", required = false) String email,
                                                                 @RequestParam(name = "phoneNumber", required = false) String phoneNumber,
                                                                 @PageableDefault(sort = "id", size = 10) Pageable pageable
    ) {

        return ResponseEntity.ok().body(supplierService.getSuppliers(supplierId, name, email, phoneNumber, pageable));
    }

    @GetMapping("/{supplierId}")
    public ResponseEntity<SupplierDto> getSupplierById(@PathVariable Long supplierId) {
        return ResponseEntity.ok().body(supplierService.getSupplierById(supplierId));
    }

    @PostMapping
    public ResponseEntity<SupplierDto> addSupplier(
            @RequestBody SupplierDto supplierDto) throws IOException {
        return ResponseEntity.ok().body(supplierService.addSupplier(supplierDto));
    }


    @PutMapping("/{supplierId}")
    public ResponseEntity<SupplierDto> updateSupplier(@PathVariable Long supplierId, @RequestBody SupplierDto supplierDto) {
        return ResponseEntity.ok().body(supplierService.updateSupplier(supplierId, supplierDto));
    }

    @DeleteMapping("/{supplierId}")
    public ResponseEntity<?> deleteSupplierById(@PathVariable Long supplierId) {
        return ResponseEntity.ok().body(supplierService.deleteSupplierById(supplierId));
    }

}
