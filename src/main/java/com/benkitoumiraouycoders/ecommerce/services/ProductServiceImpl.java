package com.benkitoumiraouycoders.ecommerce.services;

import com.benkitoumiraouycoders.ecommerce.dao.ProductDao;
import com.benkitoumiraouycoders.ecommerce.dtos.ProductDto;
import com.benkitoumiraouycoders.ecommerce.entities.Product;
import com.benkitoumiraouycoders.ecommerce.exceptions.EntityAlreadyExistsException;
import com.benkitoumiraouycoders.ecommerce.exceptions.EntityNotFoundException;
import com.benkitoumiraouycoders.ecommerce.handlers.ResponseDto;
import com.benkitoumiraouycoders.ecommerce.mappers.ProductMapper;
import com.benkitoumiraouycoders.ecommerce.services.inter.ImageService;
import com.benkitoumiraouycoders.ecommerce.services.inter.ProuctService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProuctService {

    private final ProductDao productDao;
    private final ProductMapper productMapper;
    private final ImageService imageService;

    @Override
    public List<ProductDto> getProductsByQuery(Long id, String name, Double price, Integer quantity, String visibility, Long categoryId) {
        List<ProductDto> productDtoList = productDao.getProductsByQuery(id, name, price, quantity, categoryId, visibility);
        for (ProductDto productDto : productDtoList) {
            productDto.setProductPrincipalImageUrl(imageService.getImagesUrlsFromAws(productDto.getProductImagePath()));
        }
        return productDtoList;
    }

    @Override
    public ProductDto getProductById(Long id) {
        List<ProductDto> productDtoList = productDao.getProductsByQuery(id, null, null, null, null, null);
        return productDtoList.stream()
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException(String.format("The product with the id %d is not found.", id)));
    }


    @Override
    public ProductDto addProduct(ProductDto productDto) {
        if (productDao.existsByName(productDto.getName())) {
            throw new EntityAlreadyExistsException(String.format("The product with the name %s  is already exists", productDto.getName()));
        }
        productDto.setId(null);

        return productMapper.modelToDto(productDao.save(productMapper.dtoToModel(productDto)));
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        ProductDto oldProductDto = getProductById(id);
        productDto.setId(oldProductDto.getId());
        Product updatedProduct = productDao.save(productMapper.dtoToModel(productDto));
        return productMapper.modelToDto(updatedProduct);
    }

    @Override
    public ResponseDto deleteProductById(Long id) {
        getProductById(id);
        productDao.deleteById(id);
        return ResponseDto.builder()
                .message("Product successfully deleted.")
                .build();
    }
}
