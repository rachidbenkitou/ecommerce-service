package com.benkitoumiraouycoders.ecommerce.dao.keycloak;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.benkitoumiraouycoders.ecommerce.dtos.ResponseDto;
import com.benkitoumiraouycoders.ecommerce.dtos.SecurityUserDto;
import com.benkitoumiraouycoders.ecommerce.exceptions.EntityAlreadyExistsException;
import com.benkitoumiraouycoders.ecommerce.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Transactional
@Repository
@RequiredArgsConstructor
public class KeycloakUsersProviderDaoImpl implements SecurityUsersProviderDao {
    @Value("${myKeycloak.users-endpoint}")
    private String usersEndpoint;
    @Value("${myKeycloak.token-endpoint}")
    private String tokenEndpoint;

    private final RestTemplate restTemplate;

    @Override
    public List<SecurityUserDto> getAllUsers(String accessToken) {
        ResponseEntity<SecurityUserDto[]> response = makeKeycloakRequest(usersEndpoint, HttpMethod.GET, accessToken, null, SecurityUserDto[].class);
        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return Arrays.asList(response.getBody());
        } else {
            throw new RuntimeException("Failed to fetch users from Keycloak");
        }
    }

    @Override
    public SecurityUserDto getUserByUsername(String username, String token) {
        ResponseEntity<SecurityUserDto[]> response = makeKeycloakRequest(usersEndpoint + "?username=" + username, HttpMethod.GET, token, null, SecurityUserDto[].class);
        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null && response.getBody().length > 0) {
            return response.getBody()[0];
        } else {
            throw new EntityNotFoundException("User not found: " + username);
        }
    }

    @Override
    public SecurityUserDto addUser(SecurityUserDto user, String token) {
        //String username = extractPreferredUsername(token);
//        SecurityUserDto existingUser = getUserByUsername(username, token);
//        if (existingUser != null) {
//            // If we found a user, then the user already exists
//            throw new EntityAlreadyExistsException("User already exists with username: " + username);
//        }
        ResponseEntity<SecurityUserDto> response = makeKeycloakRequest(usersEndpoint, HttpMethod.POST, token, user, SecurityUserDto.class);
        if (response.getStatusCode() == HttpStatus.CREATED) {
            //return getUserByUsername(username, token);
            return null;
        } else {
            throw new RuntimeException("Failed to create user in Keycloak");
        }
    }

    @Override
    public SecurityUserDto updateUser(SecurityUserDto user, String token) {
        return null;
    }

    @Override
    public ResponseDto deleteUserByUsername(String username, String token) {
        return null;
    }

    private <T, R> ResponseEntity<T> makeKeycloakRequest(String url, HttpMethod method, String token, R body, Class<T> responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<R> entity = new HttpEntity<>(body, headers);

        return restTemplate.exchange(url, method, entity, responseType);
    }


    private String extractPreferredUsername(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            String jwtToken = token.substring(7); // Remove 'Bearer ' prefix
            try {
                DecodedJWT decodedJWT = JWT.decode(jwtToken);
                return decodedJWT.getClaim("preferred_username").asString();
            } catch (Exception e) {
                // Handle exception (e.g., token parsing error)
                // Log this error or handle it according to your application's needs
                System.err.println("Error decoding JWT: " + e.getMessage());
            }
        }
        return null; // Return null or consider throwing an exception if preferred_username cannot be extracted
    }


}
