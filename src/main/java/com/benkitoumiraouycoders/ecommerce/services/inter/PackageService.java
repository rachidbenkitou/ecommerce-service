package com.benkitoumiraouycoders.ecommerce.services.inter;

import com.benkitoumiraouycoders.ecommerce.Criteria.PackageCriteria;
import com.benkitoumiraouycoders.ecommerce.dtos.PackageDto;
import com.benkitoumiraouycoders.ecommerce.exceptions.EntityNotFoundException;
import com.benkitoumiraouycoders.ecommerce.handlers.ResponseDto;

import java.util.List;

public interface PackageService {
    public List<PackageDto> findPackagesByCriteria(PackageCriteria packageCriteria) ;
    public PackageDto findPackagesById(Long id) throws EntityNotFoundException;
    public PackageDto persistPackages(PackageDto packagesDto) throws EntityNotFoundException ;
    public PackageDto updatePackages(Long id, PackageDto packagesDto) throws EntityNotFoundException ;
    public ResponseDto deletePackagesById(Long id) throws EntityNotFoundException ;
    }
