package com.benkitoucoders.myecommerce.controllers.api;

import com.benkitoucoders.myecommerce.dtos.address.AddressRequestDto;
import com.benkitoucoders.myecommerce.dtos.address.AddressResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface AddressApi {
    ResponseEntity<List<AddressResponseDto>> getAddresses();

    ResponseEntity<AddressResponseDto> getAddressById(@PathVariable Long addressId);

    ResponseEntity<AddressResponseDto> addAddress(@RequestBody @Valid AddressRequestDto addressRequestDto);

    ResponseEntity<AddressResponseDto> updateAddress(@RequestBody @Valid AddressRequestDto addressRequestDto);

    ResponseEntity<Void> deleteAddressById(@PathVariable Long addressId);
}
