package com.benkitoucoders.myecommerce.services.serviceinterfaces;

import com.benkitoucoders.myecommerce.dtos.address.AddressRequestDto;
import com.benkitoucoders.myecommerce.dtos.address.AddressResponseDto;

import java.util.List;

public interface AddressService {
    List<AddressResponseDto> getAddresses();

    AddressResponseDto getAddressById(Long addressId);

    AddressResponseDto createNewAddress(AddressRequestDto addressRequestDto);

    AddressResponseDto updateAddress(AddressRequestDto addressRequestDto);

    void deteteAddressById(Long addressId);
}
