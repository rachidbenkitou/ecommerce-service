package com.benkitoucoders.myecommerce.services;

import com.benkitoucoders.myecommerce.daos.GuestOrderDao;
import com.benkitoucoders.myecommerce.dtos.order.guestorder.GuestOrderRequestDto;
import com.benkitoucoders.myecommerce.dtos.order.guestorder.GuestOrderResponseDto;
import com.benkitoucoders.myecommerce.entities.order.GuestOrder;
import com.benkitoucoders.myecommerce.exceptions.guestorder.GuestAlreadyExistsException;
import com.benkitoucoders.myecommerce.exceptions.guestorder.GuestOrderNotFoundException;
import com.benkitoucoders.myecommerce.mappers.GuestOrderMapper;
import com.benkitoucoders.myecommerce.services.serviceinterfaces.GuestOrderService;
import com.benkitoucoders.myecommerce.util.ObjectFormat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class GuestOrderServiceImpl implements GuestOrderService {
    private final GuestOrderDao guestOrderDao;
    private final GuestOrderMapper guestOrderMapper;

    @Override
    public List<GuestOrderResponseDto> getGuestOrders() {
        List<GuestOrderResponseDto> guestOrderResponseDtos = null;
        log.info("guestOrderService:getguestOrders execution started.");
        List<GuestOrder> guestOrderList = guestOrderDao.findAll();
        if (!guestOrderList.isEmpty()) {
            guestOrderResponseDtos = guestOrderList.stream()
                    .map(guestOrderMapper::modelToDto)
                    .toList();
        } else {
            guestOrderResponseDtos = Collections.emptyList();
        }
        log.debug("GuestOrderService:getGuests retrieving guestOrder from database  {}", ObjectFormat.jsonAsString(guestOrderResponseDtos));
        log.info("GuestService:getGuests execution ended.");
        return guestOrderResponseDtos;
    }

    @Override
    public GuestOrderResponseDto getGuestOrderById(Long guestOrderId) {
        GuestOrderResponseDto guestOrderResponseDto;
        log.info("GuestOrderService:getGuestOrderById execution started.");
        GuestOrder guestOrder = guestOrderDao.findById(guestOrderId)
                .orElseThrow(() -> new GuestOrderNotFoundException(String.format("GuestOrder with id %d is not found", guestOrderId)));
        guestOrderResponseDto = guestOrderMapper.modelToDto(guestOrder);
        log.debug("GuestOrderService:getGuestOrderById retrieving guestOrder from database for id {} {}", guestOrderId, ObjectFormat.jsonAsString(guestOrderResponseDto));
        log.info("GuestOrderService:getGuestOrderById execution ended.");
        return guestOrderResponseDto;
    }

    @Override
    public GuestOrderResponseDto createNewGuestOrder(GuestOrderRequestDto guestOrderRequestDto) {
        return processGuestOrder(guestOrderRequestDto, "createNewGuestOrder");
    }

    @Override
    public GuestOrderResponseDto updateGuestOrder(GuestOrderRequestDto guestOrderRequestDto) {
        return processGuestOrder(guestOrderRequestDto, "updateGuestOrder");
    }

    private GuestOrderResponseDto processGuestOrder(GuestOrderRequestDto guestOrderRequestDto, String guestOrderFunctionName) {
        GuestOrderResponseDto guestOrderResponseDto;

        log.info(String.format("GuestOrderService:%s execution started.", guestOrderFunctionName));
        GuestOrder guestOrder = guestOrderMapper.dtoToModule(guestOrderRequestDto);
        log.debug(String.format("GuestOrderService:%s request parameters {}", guestOrderFunctionName), ObjectFormat.jsonAsString(guestOrderRequestDto));

            /*
            To ensure the guestOrder's existence, we perform a verification step when creating a guestOrder.
            This verification is not necessary when updating a guestOrder since we already know it exists.
             */
        if (guestOrderFunctionName.equals("createNewGuestOrder") && guestOrderDao.existsById(guestOrderRequestDto.getId()))
            throw new GuestAlreadyExistsException(String.format("The guest with id %d is already exists.", guestOrderRequestDto.getId()));
        GuestOrder guestOrderResults = guestOrderDao.save(guestOrder);
        guestOrderResponseDto = guestOrderMapper.modelToDto(guestOrderResults);
        log.debug(String.format("GuestOrderService:%s received response from Database {}", guestOrderFunctionName), ObjectFormat.jsonAsString(guestOrderRequestDto));

        log.info(String.format("GuestOrderService:%s execution ended.", guestOrderFunctionName));
        return guestOrderResponseDto;
    }


    @Override
    public void deteteGuestOrderById(Long guestOrderId) {
        log.info("GuestOrderService:deleteGuestOrder execution started.");
        guestOrderDao.deleteById(guestOrderId);
        log.debug("GuestOrderService:deleteGuestOrder guestOrder is deleted from the Database");

        log.info("GuestOrderService:deleteGuestOrder execution ended.");

    }
}
