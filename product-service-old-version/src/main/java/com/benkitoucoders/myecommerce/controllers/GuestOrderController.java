package com.benkitoucoders.myecommerce.controllers;


import com.benkitoucoders.myecommerce.controllers.api.GuestOrderApi;
import com.benkitoucoders.myecommerce.dtos.order.guestorder.GuestOrderRequestDto;
import com.benkitoucoders.myecommerce.dtos.order.guestorder.GuestOrderResponseDto;
import com.benkitoucoders.myecommerce.services.serviceinterfaces.GuestOrderService;
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
public class GuestOrderController implements GuestOrderApi {
    private final GuestOrderService guestOrderService;

    @Override
    public ResponseEntity<List<GuestOrderResponseDto>> getGuestOrders() {
        List<GuestOrderResponseDto> guestOrders = guestOrderService.getGuestOrders();
        log.info("GuestOrderController::getGuestOrders response {}", ObjectFormat.jsonAsString(guestOrders));
        return new ResponseEntity<>(guestOrders, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<GuestOrderResponseDto> getGuestOrderById(Long guestOrderId) {
        GuestOrderResponseDto guestOrderResponseDto = guestOrderService.getGuestOrderById(guestOrderId);
        log.info("GuestOrderController::getGuestOrder response {}", ObjectFormat.jsonAsString(guestOrderResponseDto));
        return new ResponseEntity<>(guestOrderResponseDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<GuestOrderResponseDto> addGuestOrder(GuestOrderRequestDto guestOrderRequestDto) {
        GuestOrderResponseDto guestOrderResponseDto = guestOrderService.createNewGuestOrder(guestOrderRequestDto);
        return processGuestOrder(guestOrderResponseDto, "addGuestOrder", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<GuestOrderResponseDto> updateGuestOrder(GuestOrderRequestDto guestOrderRequestDto) {
        GuestOrderResponseDto guestOrderResponseDto = guestOrderService.updateGuestOrder(guestOrderRequestDto);
        return processGuestOrder(guestOrderResponseDto, "updateGuestOrder", HttpStatus.OK);
    }

    private ResponseEntity<GuestOrderResponseDto> processGuestOrder(GuestOrderResponseDto guestOrderResponseDto, String guestOrderFunctionName, HttpStatus httpStatus) {
        log.info(String.format("GuestOrderController::%s response {}", guestOrderFunctionName), ObjectFormat.jsonAsString(guestOrderResponseDto));
        return new ResponseEntity<>(guestOrderResponseDto, httpStatus);
    }

    @Override
    public ResponseEntity<Void> deleteGuestOrderById(Long guestOrderId) {
        guestOrderService.deteteGuestOrderById(guestOrderId);
        log.info(String.format("GuestOrderController::GuestOrderController guestOrder %s deleted", guestOrderId));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
