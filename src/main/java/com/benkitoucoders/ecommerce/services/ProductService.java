package com.benkitoucoders.ecommerce.services;

import com.benkitoucoders.ecommerce.daos.ProductDao;
import com.benkitoucoders.ecommerce.dtos.ProductDto;
import com.benkitoucoders.ecommerce.entities.Product;
import com.benkitoucoders.ecommerce.exceptions.EntityAlreadyExistsException;
import com.benkitoucoders.ecommerce.exceptions.EntityNotFoundException;
import com.benkitoucoders.ecommerce.mappers.ProductMapper;
import com.benkitoucoders.ecommerce.services.inter.ProuctServiceInter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService implements ProuctServiceInter {

    private final ProductDao productDao;
    private final ProductMapper productMapper;

    @Override
    public List<ProductDto> getProductsByQuery(Long id, String name, Double price, Integer quantity, Long categoryId) {
        List<ProductDto> productDtoList = productDao.getProductsByQuery(id, name, price, quantity, categoryId);
        return Optional.ofNullable(productDtoList)
                .filter(list -> !list.isEmpty())
                .orElse(Collections.emptyList());
    }

    @Override
    public ProductDto getProductById(Long id) {

        Optional<Product> product = productDao.findById(id);

        if (product.isPresent()) {
            return productMapper.modelToDto(product.get());

        } else {
            throw new EntityNotFoundException(String.format("The product with the id %d is not found.", id));
        }

/*
        List<ProductDto> productDtoList = productDao.getProductsByQuery(id, null,null,null,null);
        return productDtoList.stream()
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException(String.format("The product with the id %d is not found.", id)));

 */
    }


    @Override
    public ProductDto addProduct(ProductDto productDto) {
        if (productDao.existsByName(productDto.getName())) {
            throw new EntityAlreadyExistsException(String.format("The product with the name %s and ID %d is already exists", productDto.getName(), productDto.getId()));
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
    public void deleteProductById(Long id) {
        getProductById(id);
        productDao.deleteById(id);
    }
}
