package com.benkitoucoders.myecommerce.services;

import com.benkitoucoders.myecommerce.daos.SubCategoryDao;
import com.benkitoucoders.myecommerce.dtos.SubCategoryRequestDto;
import com.benkitoucoders.myecommerce.dtos.SubCategoryResponseDto;
import com.benkitoucoders.myecommerce.entities.SubCategory;
import com.benkitoucoders.myecommerce.exceptions.subcategory.SubCategoryAlreadyExistsException;
import com.benkitoucoders.myecommerce.exceptions.subcategory.SubCategoryNotFoundException;
import com.benkitoucoders.myecommerce.exceptions.subcategory.SubCategoryServiceBusinessException;
import com.benkitoucoders.myecommerce.mappers.SubCategoryMapper;
import com.benkitoucoders.myecommerce.services.serviceinterfaces.SubCategoryService;
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
public class SubCategoryServiceImpl implements SubCategoryService {
    private final SubCategoryDao subCategoryDao;
    private final SubCategoryMapper subCategoryMapper;
    @Override
    public List<SubCategoryResponseDto> getSubCategories() {
        List<SubCategoryResponseDto> subCategoryResponseDtos = null;
        try {
            log.info("SubCategoryService:getSuvCategories execution started.");
            List<SubCategory> subCategoryList = subCategoryDao.findAll();
            if (!subCategoryList.isEmpty()) {
                subCategoryResponseDtos = subCategoryList.stream()
                        .map(subCategoryMapper::modelToDto)
                        .toList();
            } else {
                subCategoryResponseDtos = Collections.emptyList();
            }
            log.debug("SubCategoryService:getSubCategories retrieving subCategories from database  {}", ObjectFormat.jsonAsString(subCategoryResponseDtos));
        } catch (Exception ex) {
            log.error("Exception occurred while retrieving subCategories from database , Exception message {}", ex.getMessage());
            throw new SubCategoryServiceBusinessException("Exception occurred while fetch all subCategories from Database");
        }
        log.info("SubCategoryService:getSubCategories execution ended.");
        return subCategoryResponseDtos;
    }

    @Override
    public SubCategoryResponseDto getSubCategoryByName(String subCategoryName) {
        SubCategoryResponseDto subCategoryResponseDto;
        try {
            log.info("subCategoryService:getsubCategoryByName execution started.");
            SubCategory subCategory = subCategoryDao.findSubCategoryByName(subCategoryName)
                    .orElseThrow(() -> new SubCategoryNotFoundException(String.format("SubCategory not found with name %s", subCategoryName)));
            subCategoryResponseDto = subCategoryMapper.modelToDto(subCategory);
            log.debug("subCategoryService:getsubCategoryByName retrieving subCategory from database for id {} {}", subCategoryName, ObjectFormat.jsonAsString(subCategoryResponseDto));
        } catch (Exception ex) {
            log.error("Exception occurred while retrieving subCategory {} from database , Exception message {}", subCategoryName, ex.getMessage());
            throw new SubCategoryServiceBusinessException("Exception occurred while fetching subCategory from Database " );
        }
        log.info("subCategoryService:getsubCategoryByNam execution ended.");
        return subCategoryResponseDto;

    }

    @Override
    public SubCategoryResponseDto createNewSubCategory(SubCategoryRequestDto subCategoryRequestDto) {
        return processSubCategory(subCategoryRequestDto, "createNewSubCategory");
    }

    @Override
    public SubCategoryResponseDto updateSubCategory(SubCategoryRequestDto subCategoryRequestDto) {
        return processSubCategory(subCategoryRequestDto, "updateSubCategory");
    }


    /**
     * Processes the SubCategory based on the provided SubCategory request data and the SubCategory function name.
     *
     * @param subCategoryRequestDto    The SubCategoryRequestDto object containing the SubCategory data.
     * @param subCategoryFunctionName  The name of the SubCategory function being executed ("createNewSubCategory" or "updateSubCategory").
     *                              This parameter is used to differentiate between creating and updating subCategories.
     * @return The SubCategoryResponseDto object representing the processed SubCategory.
     * @throws SubCategoryAlreadyExistsException   If the SubCategory with the same name already exists (applicable for createNewSubCategory() only).
     * @throws SubCategoryServiceBusinessException If an exception occurs while processing the SubCategory.
     */
    /*
    To avoid code duplication and handle logs appropriately, a shared function called processSubCategory()
     is implemented for creating and updating subCategories. This function takes the SubCategoryFunctionName as
      a parameter to determine the specific operation being performed. When calling the function, you need
       to provide the appropriate function name: "createNewSubCategory" for creating a new SubCategory and "updateSubCategory" for updating an existing SubCategory.

     By using the SubCategoryFunctionName parameter, the function can dynamically generate log messages
     to indicate whether a SubCategory was created or updated. This helps in identifying the state of the operation
     being executed. When creating a SubCategory, the log message will indicate "created", and when updating a SubCategory,
     the log message will indicate "updated".
     */
    private SubCategoryResponseDto processSubCategory(SubCategoryRequestDto subCategoryRequestDto, String subCategoryFunctionName){
        SubCategoryResponseDto subCategoryResponseDto;

        try {
            log.info(String.format("SubCategoryService:%s execution started.", subCategoryFunctionName));
            SubCategory subCategory = subCategoryMapper.dtoToModule(subCategoryRequestDto);
            log.debug(String.format("SubCategoryService:%s request parameters {}", subCategoryFunctionName), ObjectFormat.jsonAsString(subCategoryRequestDto));

            /*
            To ensure the SubCategory's existence, we perform a verification step when creating a SubCategory.
            This verification is not necessary when updating a SubCategory since we already know it exists.
             */
            if(subCategoryFunctionName.equals("createNewSubCategory") && subCategoryDao.existsByName(subCategoryRequestDto.getName()))
                throw new SubCategoryAlreadyExistsException(String.format("The SubCategory with name %s is already exists.", subCategoryRequestDto.getName()));
            SubCategory subCategoryResults = subCategoryDao.save(subCategory);
            subCategoryResponseDto = subCategoryMapper.modelToDto(subCategoryResults);
            log.debug(String.format("SubCategoryService:%s received response from Database {}", subCategoryFunctionName), ObjectFormat.jsonAsString(subCategoryRequestDto));
        } catch (Exception ex) {
            log.error("Exception occurred while persisting SubCategory to database , Exception message {}", ex.getMessage());
            throw new SubCategoryServiceBusinessException(String.format("Exception occurred while %s", subCategoryFunctionName));
        }
        log.info(String.format("SubCategoryService:%s execution ended.", subCategoryFunctionName));
        return subCategoryResponseDto;
    }

    @Override
    public void deteteSubCategoryById(int subCategoryId) {
        try {
            log.info("subCategoryService:deletesubCategory execution started.");
            subCategoryDao.deleteById(subCategoryId);
            log.debug("subCategoryService:deletesubCategory subCategory is deleted from the Database");
        } catch (Exception ex) {
            log.error("Exception occurred while deleting subCategory {} from the database. Exception message: {}", subCategoryId, ex.getMessage());
            throw new SubCategoryServiceBusinessException("Exception occurred while deleting a subCategory");
        }
        log.info("subCategoryService:deletesubCategory execution ended.");
    }
}
