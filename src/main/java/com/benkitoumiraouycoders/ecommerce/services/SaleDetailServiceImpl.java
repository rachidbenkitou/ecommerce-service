package com.benkitoumiraouycoders.ecommerce.services;
import com.benkitoumiraouycoders.ecommerce.criteria.SaleDetailsCriteria;
import com.benkitoumiraouycoders.ecommerce.dao.SaleDetailsRepository;
import com.benkitoumiraouycoders.ecommerce.dtos.SaleDetailsDto;
import com.benkitoumiraouycoders.ecommerce.exceptions.EntityNotFoundException;
import com.benkitoumiraouycoders.ecommerce.handlers.ResponseDto;
import com.benkitoumiraouycoders.ecommerce.mappers.SaleDetailsMapper;
import com.benkitoumiraouycoders.ecommerce.services.inter.SaleDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SaleDetailServiceImpl implements SaleDetailsService {

    @Autowired
    private SaleDetailsRepository saleDetailsRepository;

    @Autowired
    private SaleDetailsMapper saleDetailsMapper;
    @Autowired
    private ProductServiceImpl productService;

    public List<SaleDetailsDto> findsaleDetailssByCriteria(SaleDetailsCriteria saleDetailsCriteria) throws EntityNotFoundException  {
    return saleDetailsRepository.getsaleDetailsByQuery(saleDetailsCriteria.getId());
    }

    public SaleDetailsDto findsaleDetailssById(Long id) throws EntityNotFoundException
    {
        SaleDetailsCriteria saleDetailsCriteria = new SaleDetailsCriteria();
        saleDetailsCriteria.setId(id);
        List<SaleDetailsDto> saleDetailsDtoList = findsaleDetailssByCriteria(saleDetailsCriteria);
        if (saleDetailsDtoList != null && !saleDetailsDtoList.isEmpty()) {
            return saleDetailsDtoList.get(0);
        } else {
            throw new EntityNotFoundException("The details of sale with the id "+id+ "  is not found.");
        }
    }


    public SaleDetailsDto persistsaleDetailss(SaleDetailsDto saleDetailsDto) throws EntityNotFoundException {
        return  saleDetailsMapper.modelToDto(saleDetailsRepository.save(saleDetailsMapper.dtoToModel(saleDetailsDto)));
    }

    public SaleDetailsDto updatesaleDetailss(Long id, SaleDetailsDto saleDetailsDto) throws EntityNotFoundException  {
        SaleDetailsDto saleDetailsDto1 = findsaleDetailssById(id);
        saleDetailsDto1.setId(id);
        saleDetailsDto1.setDateUpdate(LocalDateTime.now());
        return saleDetailsMapper.modelToDto(saleDetailsRepository.save(saleDetailsMapper.dtoToModel(saleDetailsDto1)));
    }

    public ResponseDto deletesaleDetailssById(Long id) throws EntityNotFoundException {
        ResponseDto responseDto = new ResponseDto();
        SaleDetailsDto saleDetailsDto = findsaleDetailssById(id);
        // dans la supprission de vente si la vente est payer c'est bon si la vente est annuler il faut augmenter la quantite de produit 
        //mais je prefere de metre des api pour l'annulation des produit 
        saleDetailsRepository.deleteById(id);
        responseDto.setMessage("Detail du vente bien supprimé");
        return responseDto;
    }

}
