package com.benkitoumiraouycoders.ecommerce.services;

import com.benkitoumiraouycoders.ecommerce.Criteria.PackageProductCriteria;
import com.benkitoumiraouycoders.ecommerce.dao.PackageProductRepository;
import com.benkitoumiraouycoders.ecommerce.dtos.PackageProductDto;
import com.benkitoumiraouycoders.ecommerce.exceptions.EntityNotFoundException;
import com.benkitoumiraouycoders.ecommerce.handlers.ResponseDto;
import com.benkitoumiraouycoders.ecommerce.mappers.PackageProductMapper;
import com.benkitoumiraouycoders.ecommerce.services.inter.PackageProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PackageProductServiceImpl  implements PackageProductService {



    @Autowired
    private PackageProductRepository packageProductRepository;

    @Autowired
    private PackageProductMapper   packageProductMapper;
    

    public List<PackageProductDto> findPackageProductByCriteria(PackageProductCriteria PackageProductCriteria) throws EntityNotFoundException {

        try {
            return packageProductRepository
                    .getAllServiceOptionByQuery(
                            PackageProductCriteria.getId(),
                            PackageProductCriteria.getPackageId(),
                            PackageProductCriteria.getProductId()
                    );

        } catch (Exception e) {
            throw new EntityNotFoundException("Error while executing this method (findProductByQuery)");
        }
    }

    public PackageProductDto findPackageProductById(Long id) throws EntityNotFoundException {
        PackageProductCriteria PackageProductCriteria = new PackageProductCriteria();
        PackageProductCriteria.setId(id);
        List<PackageProductDto> PackageProductDtoList = findPackageProductByCriteria(PackageProductCriteria);
        if (PackageProductDtoList != null && !PackageProductDtoList.isEmpty()) {
            return PackageProductDtoList.get(0);
        } else {
            throw new EntityNotFoundException("�l�ment n'existe pas");
        }
    }


    public PackageProductDto persistPackageProduct(PackageProductDto PackageProductDto) throws EntityNotFoundException {
        PackageProductDto.setDateCreation(LocalDateTime.now());
        return   packageProductMapper.modelToDto(packageProductRepository.save(packageProductMapper.dtoToModel(PackageProductDto)));
    }

    public PackageProductDto updatePackageProduct(Long id, PackageProductDto PackageProductDto) throws EntityNotFoundException {

        PackageProductDto PackageProductDto1 = findPackageProductById(id);
        PackageProductDto.setId(id);
        PackageProductDto.setDateUpdate(LocalDateTime.now());
        return   packageProductMapper.modelToDto(packageProductRepository.save(  packageProductMapper.dtoToModel(
                PackageProductDto)));
    }

    public ResponseDto deletePackageProductById(Long id) throws EntityNotFoundException {
        ResponseDto responseDto = new ResponseDto();
        PackageProductDto PackageProductDto = findPackageProductById(id);
        packageProductRepository.deleteById(id);
        responseDto.setMessage("élément bien supprim�");
        return responseDto;
    }
    public ResponseDto deleteProductFromPackage(Long packageId ) throws EntityNotFoundException {
        ResponseDto responseDto = new ResponseDto();
        PackageProductCriteria packageProductCriteria=PackageProductCriteria.builder().packageId(packageId).build();
        List<PackageProductDto> findPackageProductByCriteria=findPackageProductByCriteria(packageProductCriteria);
        findPackageProductByCriteria.forEach(packageProductDto->{
            packageProductRepository.deleteById(packageProductDto.getId());
        });
        responseDto.setMessage("les éléments  bien supprimés");
        return responseDto;
    }
    
    
    

}
