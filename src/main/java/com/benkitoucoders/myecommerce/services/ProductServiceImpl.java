package com.benkitoucoders.myecommerce.services;

import com.benkitoucoders.myecommerce.daos.ProductDao;
import com.benkitoucoders.myecommerce.dtos.ProductRequestDto;
import com.benkitoucoders.myecommerce.dtos.ProductResponseDto;
import com.benkitoucoders.myecommerce.entities.Product;
import com.benkitoucoders.myecommerce.exceptions.product.ProductAlreadyExistsException;
import com.benkitoucoders.myecommerce.exceptions.product.ProductNotFoundException;
import com.benkitoucoders.myecommerce.exceptions.product.ProductServiceBusinessException;
import com.benkitoucoders.myecommerce.mappers.ProductMapper;
import com.benkitoucoders.myecommerce.services.serviceinterfaces.ProductService;
import com.benkitoucoders.myecommerce.util.ObjectFormat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
@Profile(value = {"local", "dev", "prod"})
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;
    private final ProductMapper productMapper;
    @Override
    public List<ProductResponseDto> getProducts() {
        List<ProductResponseDto> productResponseDtos = null;
        try {
            log.info("ProductService:getProducts execution started.");
            List<Product> productList = productDao.findAll();
            if (!productList.isEmpty()) {
                productResponseDtos = productList.stream()
                        .map(productMapper::modelToDto)
                        .toList();
            } else {
                productResponseDtos = Collections.emptyList();
            }
            log.debug("ProductService:getProducts retrieving products from database  {}", ObjectFormat.jsonAsString(productResponseDtos));
        } catch (Exception ex) {
            log.error("Exception occurred while retrieving products from database , Exception message {}", ex.getMessage());
            throw new ProductServiceBusinessException("Exception occurred while fetch all products from Database");
        }
        log.info("ProductService:getProducts execution ended.");
        return productResponseDtos;
    }

    @Override
    public ProductResponseDto getProductById(int productId) {
        ProductResponseDto productResponseDto;
        try {
            log.info("ProductService:getProductByName execution started.");
            Product product = productDao.findById(productId)
                    .orElseThrow(() -> new ProductNotFoundException(String.format("Product not found with id %s", productId)));
            productResponseDto = productMapper.modelToDto(product);
            log.debug("ProductService:getProductById retrieving product from database for id {} {}", productId, ObjectFormat.jsonAsString(productResponseDto));
        } catch (Exception ex) {
            log.error("Exception occurred while retrieving product {} from database , Exception message {}", productId, ex.getMessage());
            throw new ProductServiceBusinessException("Exception occurred while fetching product from Database " );
        }
        log.info("ProductService:getProductById execution ended.");
        return productResponseDto;
    }

    @Override
    public ProductResponseDto createNewProduct(ProductRequestDto productRequestDto) {
        return processProduct(productRequestDto, "createNewCategory");
    }

    @Override
    public ProductResponseDto updateProduct(ProductRequestDto productRequestDto) {
        return processProduct(productRequestDto, "updateProduct");

    }

    /**
     * Processes the Product based on the provided Product request data and the Product function name.
     *
     * @param productRequestDto    The ProductRequestDto object containing the Product data.
     * @param productFunctionName  The name of the Product function being executed ("createNewProduct" or "updateProduct").
     *                              This parameter is used to differentiate between creating and updating categories.
     * @return The ProductResponseDto object representing the processed Product.
     * @throws ProductAlreadyExistsException   If the Product with the same name already exists (applicable for createNewProduct() only).
     * @throws ProductServiceBusinessException If an exception occurs while processing the Product.
     */
    /*
    To avoid code duplication and handle logs appropriately, a shared function called processProduct()
     is implemented for creating and updating categories. This function takes the ProductFunctionName as
      a parameter to determine the specific operation being performed. When calling the function, you need
       to provide the appropriate function name: "createNewProduct" for creating a new Product and "updateProduct" for updating an existing Product.

     By using the ProductFunctionName parameter, the function can dynamically generate log messages
     to indicate whether a Product was created or updated. This helps in identifying the state of the operation
     being executed. When creating a Product, the log message will indicate "created", and when updating a Product,
     the log message will indicate "updated".
     */
    private ProductResponseDto processProduct(ProductRequestDto productRequestDto, String productFunctionName){
        ProductResponseDto productResponseDto;

        try {
            log.info(String.format("ProductService:%s execution started.", productFunctionName));
            Product product = productMapper.dtoToModule(productRequestDto);
            log.debug(String.format("ProductService:%s request parameters {}", productFunctionName), ObjectFormat.jsonAsString(productRequestDto));

            /*
            To ensure the Product's existence, we perform a verification step when creating a Product.
            This verification is not necessary when updating a Product since we already know it exists.
             */
            if(productFunctionName.equals("createNewProduct") && productDao.existsById(productRequestDto.getId()))
                throw new ProductAlreadyExistsException(String.format("The Product with name %s is already exists.", productRequestDto.getId()));
            Product productResults = productDao.save(product);
            productResponseDto = productMapper.modelToDto(productResults);
            log.debug(String.format("ProductService:%s received response from Database {}", productFunctionName), ObjectFormat.jsonAsString(productRequestDto));
        } catch (Exception ex) {
            log.error("Exception occurred while persisting Product to database , Exception message {}", ex.getMessage());
            throw new ProductServiceBusinessException(String.format("Exception occurred while %s", productFunctionName));
        }
        log.info(String.format("ProductService:%s execution ended.", productFunctionName));
        return productResponseDto;
    }

    @Override
    public void deteteProductById(int productId) {
        try {
            log.info("ProductService:deleteProduct execution started.");
            productDao.deleteById(productId);
            log.debug("ProductService:deleteProduct product deleted from the Database");
        } catch (Exception ex) {
            log.error("Exception occurred while deleting product {} from the database. Exception message: {}", productId, ex.getMessage());
            throw new ProductServiceBusinessException("Exception occurred while deleting a product");
        }
        log.info("ProductService:deleteProduct execution ended.");
    }
}

