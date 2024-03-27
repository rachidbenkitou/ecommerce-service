package com.benkitoumiraouycoders.ecommerce.services;


import com.benkitoumiraouycoders.ecommerce.dao.UpsellDao;
import com.benkitoumiraouycoders.ecommerce.dtos.UpsellDto;
import com.benkitoumiraouycoders.ecommerce.entities.Upsell;
import com.benkitoumiraouycoders.ecommerce.exceptions.EntityNotFoundException;
import com.benkitoumiraouycoders.ecommerce.exceptions.EntityServiceException;
import com.benkitoumiraouycoders.ecommerce.dtos.ResponseDto;
import com.benkitoumiraouycoders.ecommerce.mappers.UpsellMapper;
import com.benkitoumiraouycoders.ecommerce.services.inter.UpsellService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class UpsellServiceImpl implements UpsellService {

    private final UpsellDao upsellDao;

    private final UpsellMapper upsellMapper;

    @Override
    public Page<Upsell> getUpsells(Pageable pageable) {
        return upsellDao.findAll(pageable);
    }



    @Override
    public UpsellDto getUpsellById(Long id) {
        log.debug("Fetching upsell with id: {}", id);
        return upsellDao.findById(id)
                .map(upsell -> {
                    log.info("Upsell found with id: {}", id);
                    return new UpsellDto(upsell.getId(), upsell.getTitle(), upsell.getDescription(), upsell.getBottomTitle());
                })
                .orElseThrow(() -> {
                    log.error("Upsell not found with id: {}", id);
                    return new EntityNotFoundException(String.format("The upsell with the id %d is not found.", id));
                });
    }

    @Override
    public UpsellDto addUpsell(UpsellDto upsellDto) throws IOException {
        log.debug("Adding new upsell: {}", upsellDto);
        try {
            upsellDto.setId(null);
            Upsell savedUpsell = upsellDao.save(upsellMapper.dtoToModel(upsellDto));
            log.info("Upsell successfully added with id: {}", savedUpsell.getId());
            return upsellMapper.modelToDto(savedUpsell);
        } catch (Exception e) {
            log.error("An error occurred while storing the upsell: {}", upsellDto, e);
            throw new EntityServiceException("An error occurred while storing the upsell.", e);
        }
    }


    @Override
    public UpsellDto updateUpsell(Long id, UpsellDto upsellDto) {

        log.debug("Updating upsell with id: {}", id);
        try {
            UpsellDto oldUpsellDto = getUpsellById(id);
            Upsell upsellToUpdate = upsellMapper.dtoToModel(upsellDto);
            upsellToUpdate.setId(oldUpsellDto.getId()); // Preserve the original ID

            Upsell updatedUpsell = upsellDao.save(upsellToUpdate);
            log.info("Upsell successfully updated for id: {}", id);
            return upsellMapper.modelToDto(updatedUpsell);
        } catch (Exception e) {
            log.error("An error occurred while updating the upsell with id: {}", id, e);
            throw new EntityServiceException("An error occurred while updating the upsell.", e);
        }
    }

    @Override
    public ResponseDto deleteUpsellById(Long id) {
        log.debug("Deleting upsell with id: {}", id);
        try {
            if (getUpsellById(id) != null) {
                upsellDao.deleteById(id);
                log.info("Upsell successfully deleted with id: {}", id);
                return ResponseDto.builder()
                        .message("Upsell successfully deleted.")
                        .build();
            } else {
                log.warn("Attempted to delete upsell, but it was not found with id: {}", id);
                throw new EntityServiceException("The id is not found");
            }
        } catch (Exception e) {
            log.error("An error occurred while deleting the upsell with id: {}", id, e);
            throw new EntityServiceException("An error occurred while deleting the upsell.", e);
        }
    }


}
