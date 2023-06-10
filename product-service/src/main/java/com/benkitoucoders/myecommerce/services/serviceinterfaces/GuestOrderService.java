package com.benkitoucoders.myecommerce.services.serviceinterfaces;

import com.benkitoucoders.myecommerce.dtos.order.guestorder.GuestOrderRequestDto;
import com.benkitoucoders.myecommerce.dtos.order.guestorder.GuestOrderResponseDto;

import java.util.List;

public interface GuestOrderService {
    List<GuestOrderResponseDto> getGuestOrders();

    GuestOrderResponseDto getGuestOrderById(Long guestOrderId);

    GuestOrderResponseDto createNewGuestOrder(GuestOrderRequestDto guestOrderRequestDto);

    GuestOrderResponseDto updateGuestOrder(GuestOrderRequestDto guestOrderRequestDto);

    void deteteGuestOrderById(Long guestOrderId);
}
