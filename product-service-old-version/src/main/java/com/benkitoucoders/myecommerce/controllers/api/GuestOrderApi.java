package com.benkitoucoders.myecommerce.controllers.api;

import com.benkitoucoders.myecommerce.dtos.order.guestorder.GuestOrderRequestDto;
import com.benkitoucoders.myecommerce.dtos.order.guestorder.GuestOrderResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface GuestOrderApi {
    ResponseEntity<List<GuestOrderResponseDto>> getGuestOrders();

    ResponseEntity<GuestOrderResponseDto> getGuestOrderById(@PathVariable Long guestOrderId);

    ResponseEntity<GuestOrderResponseDto> addGuestOrder(@RequestBody @Valid GuestOrderRequestDto guestOrderRequestDto);

    ResponseEntity<GuestOrderResponseDto> updateGuestOrder(@RequestBody @Valid GuestOrderRequestDto guestOrderRequestDto);

    ResponseEntity<Void> deleteGuestOrderById(@PathVariable Long guestOrderId);
}
