package com.benkitoumiraouycoders.ecommerce.controllers;

import com.benkitoumiraouycoders.ecommerce.dao.keycloak.SecurityUsersProviderDao;
import com.benkitoumiraouycoders.ecommerce.dtos.SecurityUserDto;
import com.benkitoumiraouycoders.ecommerce.exceptions.EntityAlreadyExistsException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class KeycloakUsersProviderController {
    private final SecurityUsersProviderDao securityUsersProviderDao;

    @GetMapping
    public List<SecurityUserDto> getUsers(HttpServletRequest request) {
        String token = extractToken(request);
        return securityUsersProviderDao.getAllUsers(token);
    }

    @GetMapping("/{username}")
    public SecurityUserDto getUserByUsername(@PathVariable String username, HttpServletRequest request) {
        String token = extractToken(request);
        return securityUsersProviderDao.getUserByUsername(username, token);
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody SecurityUserDto user, HttpServletRequest request) {
        try {
            String token = extractToken(request);
            SecurityUserDto createdUser = securityUsersProviderDao.addUser(user, token);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (EntityAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
    private String extractToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        throw new RuntimeException("No OAuth token found in the request");
    }


}
