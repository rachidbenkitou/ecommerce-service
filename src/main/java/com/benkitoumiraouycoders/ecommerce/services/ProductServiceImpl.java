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
import com.benkitoumiraouycoders.ecommerce.services.strategy.inter.ImagesUploadStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProuctService {

    private final ProductDao productDao;
    private final ProductMapper productMapper;
    private final ImageService imageService;
    private final ImagesUploadStrategy imagesUploadStrategy;

    @Override
    public List<ProductDto> getProductsByQuery(Long id, String name, Double price, Integer quantity, String visibility, Long categoryId) {
        return productDao.getProductsByQuery(id, name, price, quantity, categoryId, visibility);
    }

    @Override
    public ProductDto getProductById(Long id) {
        List<ProductDto> productDtoList = productDao.getProductsByQuery(id, null, null, null, null, null);
        return productDtoList.stream()
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException(String.format("The product with the id %d is not found.", id)));
    }


    @Override
    public ProductDto addProduct(ProductDto productDto) throws IOException {
        if (productDao.existsByName(productDto.getName())) {
            throw new EntityAlreadyExistsException(String.format("The product with the name %s  is already exists", productDto.getName()));
        }
        productDto.setId(null);
        if (productDto.getProductImages() != null && !productDto.getProductImages().isEmpty()) {
            productDto.setProductImagePath(productDto.getProductImages().get(0).getOriginalFilename());
        } else {
            throw new RuntimeException("At least one image is required.");
        }
        ProductDto savedProductDto = productMapper.modelToDto(productDao.save(productMapper.dtoToModel(productDto)));
        if (savedProductDto != null) {
            imagesUploadStrategy.uploadImages(productDto.getProductImages(), savedProductDto.getId());
            return savedProductDto;
        } else {
            throw new RuntimeException("Error while saving the product.");
        }
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
