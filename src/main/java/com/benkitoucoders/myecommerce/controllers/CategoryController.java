package com.benkitoucoders.myecommerce.controllers;

import com.benkitoucoders.myecommerce.controllers.api.CategoryApi;
import com.benkitoucoders.myecommerce.dtos.CategoryResponseDto;
import com.benkitoucoders.myecommerce.services.serviceinterfaces.CategoryService;
import com.benkitoucoders.myecommerce.util.ObjectFormat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CategoryController implements CategoryApi {
    private final CategoryService categoryService;
    @Override
    public ResponseEntity<List<CategoryResponseDto>> getCategories(){
        List<CategoryResponseDto> categories = categoryService.getCategories();
        log.info("CategoryController::getCategories response {}", ObjectFormat.jsonAsString(categories));
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
