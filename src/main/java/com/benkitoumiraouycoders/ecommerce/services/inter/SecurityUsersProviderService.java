package com.benkitoumiraouycoders.ecommerce.services.inter;

import com.benkitoumiraouycoders.ecommerce.dtos.ResponseDto;
import com.benkitoumiraouycoders.ecommerce.dtos.SecurityUserDto;

import java.util.List;

public interface SecurityUsersProviderService {
    List<SecurityUserDto> getAllUsers(String accessToken);

    SecurityUserDto getUserByUsername(String username, String token);

    SecurityUserDto addUser(SecurityUserDto user, String token);

    ResponseDto updateUser(SecurityUserDto user,String id, String token);

    ResponseDto deleteUserById(String id, String token);
}
