package com.benkitoumiraouycoders.ecommerce.services;

import com.benkitoumiraouycoders.ecommerce.Criteria.PackageCriteria;
import com.benkitoumiraouycoders.ecommerce.Criteria.PackageProductCriteria;
import com.benkitoumiraouycoders.ecommerce.dao.PackageRepository;
import com.benkitoumiraouycoders.ecommerce.dtos.PackageDto;
import com.benkitoumiraouycoders.ecommerce.dtos.PackageProductDto;
import com.benkitoumiraouycoders.ecommerce.dtos.ProductDto;
import com.benkitoumiraouycoders.ecommerce.exceptions.EntityNotFoundException;
import com.benkitoumiraouycoders.ecommerce.handlers.ResponseDto;
import com.benkitoumiraouycoders.ecommerce.mappers.PackageMapper;
import com.benkitoumiraouycoders.ecommerce.services.inter.PackageProductService;
import com.benkitoumiraouycoders.ecommerce.services.inter.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PackageServiceImpl implements PackageService {

    @Autowired
    private PackageRepository packageRepository;
    @Autowired
    private PackageMapper packageMapper;
    
    @Autowired
    private PackageProductService packageProductService;
    @Autowired
    private ProductService productService;
    public List<PackageDto> findPackagesByCriteria(PackageCriteria packageCriteria)  {
        List<PackageDto> packageDtosList=packageRepository.getAllPackageByQuery(packageCriteria.getId(),packageCriteria.getName(),packageCriteria.getActive());
        PackageProductCriteria packageProductCriteria=new PackageProductCriteria();
        List<PackageProductDto> packageProductDtoList=new ArrayList<>();
        ProductDto productDto1=null;

        if(packageDtosList.isEmpty())
            return new ArrayList<>();

        for (PackageDto packageDto : packageDtosList) {

            packageProductCriteria.setPackageId(packageDto.getId());
            packageProductDtoList=packageProductService.findPackageProductByCriteria(packageProductCriteria);
            for (PackageProductDto packageProductDto : packageProductDtoList) {
                productDto1=productService.getProductById(packageProductDto.getProductId());
                packageDto.getProductDtos().add(productDto1);
            }
        }
     return packageDtosList;
    }
    public PackageDto findPackagesById(Long id) throws EntityNotFoundException {
        PackageCriteria packageCriteria=PackageCriteria.builder().id(id).build();
        List<PackageDto> packageDtosList=packageRepository.getAllPackageByQuery(
                packageCriteria.getId(),packageCriteria.getName(),packageCriteria.getActive());
       if(packageDtosList.isEmpty()){
           throw new EntityNotFoundException("this package n'existe pas ");
       }
       return packageDtosList.get(0);
    }

    public PackageDto persistPackages(PackageDto packagesDto) throws EntityNotFoundException {
        PackageDto packageDto=packageMapper.modelToDto(packageRepository.save(packageMapper.dtoToModel(packagesDto)));
        if(packageDto.getProductDtos()!=null){
            PackageProductDto packageProductDto=new PackageProductDto();
            packageDto.getProductDtos().forEach(productDto -> {
                ProductDto productDto1 = productService.addProduct(productDto);
                packageProductDto.setProductId(productDto1.getId());
                packageProductDto.setPackageId(packageDto.getId());
                packageProductService.persistPackageProduct(packageProductDto);
            });
        }
        return packageDto;
    }

    public PackageDto updatePackages(Long id, PackageDto packagesDto) throws EntityNotFoundException {
        // delete the old product from details and add new product
        return null;
    }

    public ResponseDto deletePackagesById(Long id) throws EntityNotFoundException {
        ResponseDto responseDto = new ResponseDto();
        PackageDto packagesDto = findPackagesById(id);
        // verifier si packege deja vendu
        packageProductService.deleteProductFromPackage(packagesDto.getId());
        packageRepository.deleteById(id);
        responseDto.setMessage("élément bien supprimé");
        return responseDto;
    }

}
