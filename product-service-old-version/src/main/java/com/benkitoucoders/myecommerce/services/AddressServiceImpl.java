package com.benkitoucoders.myecommerce.services;

import com.benkitoucoders.myecommerce.daos.AddressDao;
import com.benkitoucoders.myecommerce.dtos.address.AddressRequestDto;
import com.benkitoucoders.myecommerce.dtos.address.AddressResponseDto;
import com.benkitoucoders.myecommerce.entities.Address;
import com.benkitoucoders.myecommerce.exceptions.address.AddressAlreadyExistsException;
import com.benkitoucoders.myecommerce.exceptions.address.AddressNotFoundException;
import com.benkitoucoders.myecommerce.mappers.AddressMapper;
import com.benkitoucoders.myecommerce.services.serviceinterfaces.AddressService;
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
public class AddressServiceImpl implements AddressService {
    private final AddressDao addressDao;
    private final AddressMapper addressMapper;

    @Override
    public List<AddressResponseDto> getAddresses() {
        List<AddressResponseDto> addressResponseDtos = null;
        log.info("AddressService:getAddresses execution started.");
        List<Address> addressList = addressDao.findAll();
        if (!addressList.isEmpty()) {
            addressResponseDtos = addressList.stream()
                    .map(addressMapper::modelToDto)
                    .toList();
        } else {
            addressResponseDtos = Collections.emptyList();
        }
        log.debug("AddressService:getAddresses retrieving address from database  {}", ObjectFormat.jsonAsString(addressResponseDtos));
        log.info("AddressService:getAddresses execution ended.");
        return addressResponseDtos;
    }

    @Override
    public AddressResponseDto getAddressById(Long addressId) {
        AddressResponseDto addressResponseDto;
        log.info("AddressService:getAddressById execution started.");
        Address address = addressDao.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundException(String.format("Address with id %d is not found", addressId)));
        addressResponseDto = addressMapper.modelToDto(address);
        log.debug("addressService:getaddressById retrieving address from database for id {} {}", addressId, ObjectFormat.jsonAsString(addressResponseDto));
        log.info("addressService:getaddressById execution ended.");
        return addressResponseDto;
    }

    @Override
    public AddressResponseDto createNewAddress(AddressRequestDto addressRequestDto) {
        return processAddress(addressRequestDto, "createNewAddress");
    }

    @Override
    public AddressResponseDto updateAddress(AddressRequestDto addressRequestDto) {
        return processAddress(addressRequestDto, "createNewAddress");
    }

    private AddressResponseDto processAddress(AddressRequestDto addressRequestDto, String addressFunctionName) {
        AddressResponseDto addressResponseDto;

        log.info(String.format("AddressService:%s execution started.", addressFunctionName));
        Address address = addressMapper.dtoToModule(addressRequestDto);
        log.debug(String.format("AddressService:%s request parameters {}", addressFunctionName), ObjectFormat.jsonAsString(addressRequestDto));

            /*
            To ensure the address's existence, we perform a verification step when creating a address.
            This verification is not necessary when updating a address since we already know it exists.
             */
        if (addressFunctionName.equals("createNewaddress") && addressDao.existsById(addressRequestDto.getId()))
            throw new AddressAlreadyExistsException(String.format("The address with id %d is already exists.", addressRequestDto.getId()));
        Address addressResults = addressDao.save(address);
        addressResponseDto = addressMapper.modelToDto(addressResults);
        log.debug(String.format("AddressService:%s received response from Database {}", addressFunctionName), ObjectFormat.jsonAsString(addressRequestDto));

        log.info(String.format("AddressService:%s execution ended.", addressFunctionName));
        return addressResponseDto;
    }

    @Override
    public void deteteAddressById(Long addressId) {
        log.info("AddressService:deleteAddress execution started.");
        addressDao.deleteById(addressId);
        log.debug("AddressService:deleteAddress address is deleted from the Database");

        log.info("AddressService:deleteAddress execution ended.");

    }
}
