package com.benkitoucoders.myecommerce.services.serviceinterfaces;

import com.benkitoucoders.myecommerce.dtos.category.CategoryRequestDto;
import com.benkitoucoders.myecommerce.dtos.category.CategoryResponseDto;
import com.benkitoucoders.myecommerce.exceptions.category.CategoryAlreadyExistsException;
import com.benkitoucoders.myecommerce.exceptions.category.CategoryNotFoundException;

import java.util.List;

public interface CategoryService {

    /**
     * Retrieves all categories from the database.
     *
     * @return A list of CategoryResponseDto objects representing the categories.
     * @throws CategoryServiceBusinessException If an exception occurs while fetching the categories.
     */
    List<CategoryResponseDto> getCategories();

    /**
     * Retrieves a category by its name from the database.
     *
     * @param categoryName The name of the category to retrieve.
     * @return The CategoryResponseDto object representing the category.
     * @throws CategoryNotFoundException        If the category with the specified name is not found.
     * @throws CategoryServiceBusinessException If an exception occurs while fetching the category.
     */
    CategoryResponseDto getCategoryByName(String categoryName);

    /**
     * Creates a new category based on the provided category request data.
     *
     * @param categoryRequestDto The CategoryRequestDto object containing the category data.
     * @return The CategoryResponseDto object representing the newly created category.
     * @throws CategoryAlreadyExistsException   If the category with the same name already exists (applicable for createNewCategory() only).
     * @throws CategoryServiceBusinessException If an exception occurs while processing the category.
     */
    CategoryResponseDto createNewCategory(CategoryRequestDto categoryRequestDto);

    /**
     * Updates an existing category based on the provided category request data.
     *
     * @param categoryRequestDto The CategoryRequestDto object containing the category data.
     * @return The CategoryResponseDto object representing the updated category.
     * @throws CategoryServiceBusinessException If an exception occurs while processing the category.
     */
    CategoryResponseDto updateCategory(CategoryRequestDto categoryRequestDto);

    /**
     * Deletes a category by its ID.
     *
     * @param categoryId the ID of the category to delete
     * @throws CategoryServiceBusinessException if an exception occurs while deleting the category
     */
    void deteteCategoryById(int categoryId);
}
