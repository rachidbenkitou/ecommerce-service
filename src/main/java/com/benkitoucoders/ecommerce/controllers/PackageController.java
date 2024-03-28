package com.benkitoucoders.ecommerce.controllers;

import com.benkitoucoders.ecommerce.exceptions.EntityNotFoundException;
import com.benkitoucoders.ecommerce.services.inter.PackageService;
import com.benkitoucoders.ecommerce.criteria.PackageCriteria;
import com.benkitoucoders.ecommerce.dtos.PackageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/package")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:58213", allowCredentials = "true")
public class PackageController {

    private final PackageService packageService;
    
    @GetMapping
    public ResponseEntity<?> findpackagesByCriteria(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "id", required = false) Long id,
            @RequestParam(name = "active", required = false) String active,
            @RequestParam(name = "isDefault", required = false) String isDefault
    ) throws EntityNotFoundException {
          PackageCriteria packagesCriteria = new PackageCriteria();
          packagesCriteria.setName(name);
          packagesCriteria.setId(id);
          packagesCriteria.setActive(active);
          packagesCriteria.setIsDefault(isDefault);
        return ResponseEntity.ok(  packageService.findPackagesByCriteria( packagesCriteria));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findpackagesById(@PathVariable Long id) throws EntityNotFoundException {
        return ResponseEntity.ok(    packageService.findPackagesById(id));
    }

    @PostMapping
    public ResponseEntity<?> persistpackages(@RequestBody PackageDto packagesDto) throws EntityNotFoundException {
        return ResponseEntity.ok(    packageService.persistPackages(packagesDto));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updatepackages(@PathVariable Long id, @RequestBody   PackageDto   packagesDto) throws EntityNotFoundException {
        return ResponseEntity.ok(packageService.updatePackages(id, packagesDto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletepackagesById(@PathVariable Long id) throws EntityNotFoundException {
        return ResponseEntity.ok(    packageService.deletePackagesById(id));
    }
}
