package com.benkitoucoders.ecommerce.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.benkitoucoders.ecommerce.dtos.ResponseDto;
import com.benkitoucoders.ecommerce.exceptions.EntityAlreadyExistsException;
import com.benkitoucoders.ecommerce.exceptions.EntityNotFoundException;
import com.benkitoucoders.ecommerce.services.inter.SecurityUsersProviderService;
import com.benkitoucoders.ecommerce.dtos.SecurityUserDto;
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
public class KeycloakUsersProviderServiceImpl implements SecurityUsersProviderService {
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

    public SecurityUserDto getUserByUsernameWithoutException(String username, String token) {
        ResponseEntity<SecurityUserDto[]> response = makeKeycloakRequest(usersEndpoint + "?username=" + username, HttpMethod.GET, token, null, SecurityUserDto[].class);
        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null && response.getBody().length > 0) {
            return response.getBody()[0];
        } else {
            return null;
        }
    }

    public SecurityUserDto getUserById(String id, String token) {
        ResponseEntity<SecurityUserDto[]> response = makeKeycloakRequest(usersEndpoint + "?id=" + id, HttpMethod.GET, token, null, SecurityUserDto[].class);
        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null && response.getBody().length > 0) {
            return response.getBody()[0];
        } else {
            throw new EntityNotFoundException("User not found: " + id);
        }
    }

    @Override
    public SecurityUserDto addUser(SecurityUserDto user, String token) {
        String username = extractPreferredUsername(token);
        SecurityUserDto existingUser = getUserByUsernameWithoutException(username, token);
        if (existingUser != null) {
            // If we found a user, then the user already exists
            throw new EntityAlreadyExistsException("User already exists with username: " + username);
        }
        ResponseEntity<SecurityUserDto> response = makeKeycloakRequest(usersEndpoint, HttpMethod.POST, token, user, SecurityUserDto.class);
        if (response.getStatusCode() == HttpStatus.CREATED) {
            return getUserByUsername(user.getUsername(), token);
        } else {
            throw new RuntimeException("Failed to create user in Keycloak");
        }
    }

    @Override
    public ResponseDto updateUser(SecurityUserDto user, String id, String token) {
        if (getUserById(id, token) == null) {
            throw new EntityNotFoundException("The user with the id" + id + "is not found");
        }
        ResponseEntity<SecurityUserDto> response = makeKeycloakRequest(usersEndpoint + "/" + id, HttpMethod.PUT, token, user, SecurityUserDto.class);

        if (response.getStatusCode() == HttpStatus.NO_CONTENT) {
            return ResponseDto.builder()
                    .message("User updated successfully.")
                    .build();
        } else {
            throw new RuntimeException("Failed to update user in Keycloak");
        }
    }

    @Override
    public ResponseDto deleteUserById(String id, String token) {
        ResponseEntity<Void> response = makeKeycloakRequest(usersEndpoint + "/" + id, HttpMethod.DELETE, token, null, Void.class);
        if (response.getStatusCode() == HttpStatus.NO_CONTENT) {
            return ResponseDto.builder()
                    .message("User deleted successfully.")
                    .build();
        } else {
            throw new RuntimeException("Failed to delete user in Keycloak");
        }
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
