package com.benkitoumiraouycoders.ecommerce.dao.keycloak;

import com.benkitoumiraouycoders.ecommerce.dtos.ResponseDto;
import com.benkitoumiraouycoders.ecommerce.dtos.SecurityUserDto;
import com.benkitoumiraouycoders.ecommerce.entities.User;
import com.benkitoumiraouycoders.ecommerce.utils.KeycloakEndPoints;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class KeycloakUsersProviderDaoImpl implements SecurityUsersProviderDao {

    private final String usersEndpoint = KeycloakEndPoints.KEYCLOAK_USERS_ENDPOINT;
    private final String tokenEndpoint = KeycloakEndPoints.KEYCLOAK_TOKEN_ENDPOINT;

    @Override
    public List<SecurityUserDto> getAllUsers() {
        return null;
    }

    @Override
    public SecurityUserDto getUserByUsername(String username) {
        return null;
    }

    @Override
    public SecurityUserDto addUser(User user) {
        return null;
    }

    @Override
    public SecurityUserDto updateUser(User user) {
        return null;
    }

    @Override
    public ResponseDto deleteUserByUsername(String username) {
        return null;
    }
}
