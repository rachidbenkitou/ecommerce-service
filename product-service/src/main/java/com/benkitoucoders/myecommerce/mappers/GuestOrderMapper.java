package com.benkitoucoders.myecommerce.mappers;

import com.benkitoucoders.myecommerce.dtos.order.guestorder.GuestOrderRequestDto;
import com.benkitoucoders.myecommerce.dtos.order.guestorder.GuestOrderResponseDto;
import com.benkitoucoders.myecommerce.entities.order.GuestOrder;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
@Service
public interface GuestOrderMapper {
    GuestOrderResponseDto modelToDto(GuestOrder guestOrder);
    GuestOrder dtoToModule(GuestOrderRequestDto guestOrderRequestDto);
}
