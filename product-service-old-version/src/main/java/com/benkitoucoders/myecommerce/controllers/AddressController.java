package com.benkitoucoders.myecommerce.controllers;


import com.benkitoucoders.myecommerce.controllers.api.AddressApi;
import com.benkitoucoders.myecommerce.dtos.address.AddressRequestDto;
import com.benkitoucoders.myecommerce.dtos.address.AddressResponseDto;
import com.benkitoucoders.myecommerce.services.serviceinterfaces.AddressService;
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
public class AddressController implements AddressApi {
    private final AddressService addressService;

    @Override
    public ResponseEntity<List<AddressResponseDto>> getAddresses() {
        List<AddressResponseDto> addresss = addressService.getAddresses();
        log.info("AddressController::getAddresses response {}", ObjectFormat.jsonAsString(addresss));
        return new ResponseEntity<>(addresss, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AddressResponseDto> getAddressById(Long addressId) {
        AddressResponseDto addressResponseDto = addressService.getAddressById(addressId);
        log.info("AddressController::getAddress response {}", ObjectFormat.jsonAsString(addressResponseDto));
        return new ResponseEntity<>(addressResponseDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AddressResponseDto> addAddress(AddressRequestDto addressRequestDto) {
        AddressResponseDto addressResponseDto = addressService.createNewAddress(addressRequestDto);
        return processAddress(addressResponseDto, "addAddress", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<AddressResponseDto> updateAddress(AddressRequestDto addressRequestDto) {
        AddressResponseDto addressResponseDto = addressService.updateAddress(addressRequestDto);
        return processAddress(addressResponseDto, "updateAddress", HttpStatus.OK);
    }

    private ResponseEntity<AddressResponseDto> processAddress(AddressResponseDto addressResponseDto, String addressFunctionName, HttpStatus httpStatus) {
        log.info(String.format("AddressController::%s response {}", addressFunctionName), ObjectFormat.jsonAsString(addressResponseDto));
        return new ResponseEntity<>(addressResponseDto, httpStatus);
    }

    @Override
    public ResponseEntity<Void> deleteAddressById(Long addressId) {
        addressService.deteteAddressById(addressId);
        log.info(String.format("AddressController::AddressController address %s deleted", addressId));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
