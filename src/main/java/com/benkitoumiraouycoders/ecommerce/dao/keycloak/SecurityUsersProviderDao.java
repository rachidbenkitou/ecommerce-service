package com.benkitoumiraouycoders.ecommerce.dao.keycloak;

import com.benkitoumiraouycoders.ecommerce.dtos.ResponseDto;
import com.benkitoumiraouycoders.ecommerce.dtos.SecurityUserDto;
import com.benkitoumiraouycoders.ecommerce.entities.User;

import java.util.List;

public interface SecurityUsersProviderDao {
    List<SecurityUserDto> getAllUsers(String accessToken);

    SecurityUserDto getUserByUsername(String username, String token);

    SecurityUserDto addUser(SecurityUserDto user, String token);

    SecurityUserDto updateUser(SecurityUserDto user, String token);

    ResponseDto deleteUserByUsername(String username, String token);
}
