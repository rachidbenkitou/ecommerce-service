package com.benkitoumiraouycoders.ecommerce.services;

import com.benkitoumiraouycoders.ecommerce.criteria.SaleCriteria;
import com.benkitoumiraouycoders.ecommerce.dao.SaleDao;
import com.benkitoumiraouycoders.ecommerce.dtos.SaleDto;
import com.benkitoumiraouycoders.ecommerce.exceptions.EntityNotFoundException;
import com.benkitoumiraouycoders.ecommerce.handlers.ResponseDto;
import com.benkitoumiraouycoders.ecommerce.mappers.SaleMapper;
import com.benkitoumiraouycoders.ecommerce.services.inter.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleDao saleDao;

    @Autowired
    private SaleMapper saleMapper;
    @Autowired
    private ProductServiceImpl productService;

    public List<SaleDto> findsalesByCriteria(SaleCriteria saleCriteria) throws EntityNotFoundException {
        return saleDao.getSalesByQuery(saleCriteria.getId(), saleCriteria.getSaleStatusId());
    }

    public SaleDto findsalesById(Long id) throws EntityNotFoundException {
        SaleCriteria saleCriteria = new SaleCriteria();
        saleCriteria.setId(id);
        List<SaleDto> saleDtoList = findsalesByCriteria(saleCriteria);
        if (saleDtoList != null && !saleDtoList.isEmpty()) {
            return saleDtoList.get(0);
        } else {
            throw new EntityNotFoundException("The sale with the id " + id + "  is not found.");
        }
    }


    public SaleDto persistsales(SaleDto saleDto) throws EntityNotFoundException {
        saleDto.setId(null);

        return saleMapper.modelToDto(saleDao.save(saleMapper.dtoToModel(saleDto)));
    }

    public SaleDto updatesales(Long id, SaleDto saleDto) throws EntityNotFoundException {
        SaleDto saleDto1 = findsalesById(id);
        saleDto1.setId(id);
        saleDto1.setDateUpdate(LocalDateTime.now());
        return saleMapper.modelToDto(saleDao.save(saleMapper.dtoToModel(saleDto1)));
    }

    public ResponseDto deletesalesById(Long id) throws EntityNotFoundException {
        ResponseDto responseDto = new ResponseDto();
        SaleDto saleDto = findsalesById(id);
        // dans la supprission de vente si la vente est payer c'est bon si la vente est annuler il faut augmenter la quantite de produit
        //mais je prefere de metre des api pour l'annulation des produit
        saleDao.deleteById(id);
        responseDto.setMessage("Sale is successfully deleted!");
        return responseDto;
    }

}
