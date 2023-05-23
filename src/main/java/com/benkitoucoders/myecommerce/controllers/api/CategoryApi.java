package com.benkitoucoders.myecommerce.controllers.api;

import com.benkitoucoders.myecommerce.dtos.CategoryRequestDto;
import com.benkitoucoders.myecommerce.dtos.CategoryResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/categories")
public interface CategoryApi {
    @GetMapping
    @Operation(summary = "Get all categories", description = "Retrieve a list of all categories.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of categories",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CategoryResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<List<CategoryResponseDto>> getCategories();

    @Operation(summary = "Get category by name", description = "Retrieve a category by its name.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the category",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CategoryResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Category not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("{categoryName}")
    ResponseEntity<CategoryResponseDto> getCategoryByName(@PathVariable String categoryName);

    @Operation(summary = "Add a new category", description = "Creates a new category. If the category already exists, a 409 Conflict status is returned.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CategoryResponseDto.class))}),
            @ApiResponse(responseCode = "409", description = "Conflict - Category already exists",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    ResponseEntity<CategoryResponseDto> addCategory(@RequestBody @Valid CategoryRequestDto categoryRequestDto);

    @Operation(summary = "Update an existing category", description = "Updates an existing category with the provided data.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CategoryResponseDto.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping
    ResponseEntity<CategoryResponseDto> updateCategory(@RequestBody @Valid CategoryRequestDto categoryRequestDto);
    @Operation(summary = "Delete a category by ID", description = "Deletes a category with the specified ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category deleted"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("{categoryId}")
    ResponseEntity<Void> deleteCategory(@PathVariable int categoryId);
}
