package com.benkitoumiraouycoders.ecommerce.services.inter;

import com.benkitoumiraouycoders.ecommerce.criteria.PackageProductCriteria;
import com.benkitoumiraouycoders.ecommerce.dtos.PackageProductDto;
import com.benkitoumiraouycoders.ecommerce.exceptions.EntityNotFoundException;
import com.benkitoumiraouycoders.ecommerce.dtos.ResponseDto;

import java.util.List;

public interface PackageProductService {
    public ResponseDto deleteProductFromPackage(Long packageId) throws EntityNotFoundException;
    public ResponseDto deletePackageProductById(Long id) throws EntityNotFoundException;
    public PackageProductDto updatePackageProduct(Long id, PackageProductDto PackageProductDto) throws EntityNotFoundException;
    public PackageProductDto persistPackageProduct(PackageProductDto PackageProductDto) throws EntityNotFoundException ;
    public PackageProductDto findPackageProductById(Long id) throws EntityNotFoundException ;
    public List<PackageProductDto> findPackageProductByCriteria(PackageProductCriteria PackageProductCriteria) throws EntityNotFoundException ;

    }
