package com.benkitoumiraouycoders.ecommerce.services;


import com.benkitoumiraouycoders.ecommerce.dao.UpsellDao;
import com.benkitoumiraouycoders.ecommerce.dtos.UpsellDto;
import com.benkitoumiraouycoders.ecommerce.entities.Upsell;
import com.benkitoumiraouycoders.ecommerce.exceptions.EntityNotFoundException;
import com.benkitoumiraouycoders.ecommerce.exceptions.EntityServiceException;
import com.benkitoumiraouycoders.ecommerce.handlers.ResponseDto;
import com.benkitoumiraouycoders.ecommerce.mappers.UpsellMapper;
import com.benkitoumiraouycoders.ecommerce.services.inter.UpsellService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
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
        return upsellDao.findById(id)
                .map(upsell -> new UpsellDto(upsell.getId(), upsell.getTitle(), upsell.getDescription(), upsell.getBottomTitle()))
                .orElseThrow(() -> new EntityNotFoundException(String.format("The upsell with the id %d is not found.", id)));
    }

    @Override
    public UpsellDto addUpsell(UpsellDto upsellDto) throws IOException {
        try {
            upsellDto.setId(null);
            Upsell saveUpsell = upsellDao.save(upsellMapper.dtoToModel(upsellDto));
            return upsellMapper.modelToDto(saveUpsell);
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while storing the upsell.", e);
        }

    }

    @Override
    public UpsellDto updateUpsell(Long id, UpsellDto upsellDto) {
        try {
            UpsellDto oldUpsellDto = getUpsellById(id);

            Upsell upsellToUpdate = upsellMapper.dtoToModel(upsellDto);
            upsellToUpdate.setId(oldUpsellDto.getId()); // Preserve the original ID

            Upsell updatedUpsell = upsellDao.save(upsellToUpdate);
            return upsellMapper.modelToDto(updatedUpsell);
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while updat the upsell.", e);
        }

    }

    @Override
    public ResponseDto deleteUpsellById(Long id) {
        getUpsellById(id);
        upsellDao.deleteById(id);
        return ResponseDto.builder()
                .message("Upsell successfully deleted.")
                .build();
    }


}
