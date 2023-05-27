package com.benkitoucoders.myecommerce.controllers;

import com.benkitoucoders.myecommerce.controllers.api.SubCategoryApi;
import com.benkitoucoders.myecommerce.dtos.SubCategoryRequestDto;
import com.benkitoucoders.myecommerce.dtos.SubCategoryResponseDto;
import com.benkitoucoders.myecommerce.services.serviceinterfaces.SubCategoryService;
import com.benkitoucoders.myecommerce.util.ObjectFormat;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SubCategoryController implements SubCategoryApi {
    private final SubCategoryService subCategoryService;
    @Override
    public ResponseEntity<List<SubCategoryResponseDto>> getSubCategories(){
        List<SubCategoryResponseDto> subCategories = subCategoryService.getSubCategories();
        log.info("SubCategoryController::getSubCategories response {}", ObjectFormat.jsonAsString(subCategories));
        return new ResponseEntity<>(subCategories, HttpStatus.OK);
    }
    @Override
    public ResponseEntity<SubCategoryResponseDto> getCategoryByName(@PathVariable String subCategoryName){
        SubCategoryResponseDto subCategoryResponseDto = subCategoryService.getSubCategoryByName(subCategoryName);
        log.info("SubCategoryController::getSubCategory response {}", ObjectFormat.jsonAsString(subCategoryResponseDto));
        return new ResponseEntity<>(subCategoryResponseDto, HttpStatus.OK);
    }
    @Override
    public ResponseEntity<SubCategoryResponseDto> addSubCategory(@RequestBody @Valid SubCategoryRequestDto subCategoryRequestDto){
        SubCategoryResponseDto  subCategoryResponseDto = subCategoryService.createNewSubCategory(subCategoryRequestDto);
        return processSubCategory(subCategoryResponseDto, "addSubCategory", HttpStatus.CREATED);
    }
    @Override
    public ResponseEntity<SubCategoryResponseDto> updateSubCategory(@RequestBody @Valid SubCategoryRequestDto subCategoryRequestDto){
        SubCategoryResponseDto  subCategoryResponseDto = subCategoryService.updateSubCategory(subCategoryRequestDto);
        return processSubCategory(subCategoryResponseDto, "updateCategory", HttpStatus.OK);
    }

    /**
     * Process the SubCategory response and return a ResponseEntity with the specified HTTP status.
     * This function is used to avoid code duplication in the addSubCategory and updateSubCategory methods.
     * It takes the SubCategory response DTO, the name of the SubCategory function being processed, and the desired HTTP status as parameters.
     * The SubCategory response DTO is logged, and a new ResponseEntity is created with the DTO and the specified HTTP status.
     *
     * @param subCategoryResponseDto  The response DTO containing the SubCategory data.
     * @param subCategoryFunctionName The name of the SubCategory function being processed.
     * @param httpStatus           The HTTP status to be returned in the ResponseEntity.
     * @return A ResponseEntity containing the SubCategory response DTO and the specified HTTP status.
     */
    private ResponseEntity<SubCategoryResponseDto> processSubCategory(SubCategoryResponseDto subCategoryResponseDto, String subCategoryFunctionName, HttpStatus httpStatus){
        log.info(String.format("SubCategoryController::%s response {}", subCategoryFunctionName), ObjectFormat.jsonAsString(subCategoryResponseDto));
        return new ResponseEntity<>(subCategoryResponseDto, httpStatus);
    }

    @Override
    public ResponseEntity<Void> deleteSubCategory(@PathVariable int subCategoryId) {
        subCategoryService.deteteSubCategoryById(subCategoryId);
        log.info(String.format("SubCategoryController::SubCategoryController subCategory %s deleted", subCategoryId));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
