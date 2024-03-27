package com.benkitoumiraouycoders.ecommerce.dao.keycloak;

import com.benkitoumiraouycoders.ecommerce.dtos.ResponseDto;
import com.benkitoumiraouycoders.ecommerce.dtos.SecurityUserDto;
import com.benkitoumiraouycoders.ecommerce.entities.User;

import java.util.List;

public interface SecurityUsersProviderDao {
    List<SecurityUserDto> getAllUsers();

    SecurityUserDto getUserByUsername(String username);

    SecurityUserDto addUser(User user);

    SecurityUserDto updateUser(User user);

    ResponseDto deleteUserByUsername(String username);
}
