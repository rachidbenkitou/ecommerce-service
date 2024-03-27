package com.benkitoumiraouycoders.ecommerce.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class KeycloakEndPoints {
    @Value("${myKeycloak.realm-id}")
    private static String realmId;
    public static String KEYCLOAK_USERS_ENDPOINT = "http://localhost:8080/admin/realms/"+realmId+"/users/";
    public static String KEYCLOAK_TOKEN_ENDPOINT = "http://localhost:8080/realms/"+realmId+"/protocol/openid-connect/token";
}
