package com.benkitoumiraouycoders.ecommerce.controllers;

import com.benkitoumiraouycoders.ecommerce.dtos.ClientStatusDto;
import com.benkitoumiraouycoders.ecommerce.mappers.ClientStatusMapper;
import com.benkitoumiraouycoders.ecommerce.services.inter.ClientStatusService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clientStatuses")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:58213", allowCredentials = "true")
public class ClientStatusController {
    private ClientStatusService clientStatusService;
    private ClientStatusMapper clientStatusMapper;

    @GetMapping
    public ResponseEntity<List<ClientStatusDto>> getClientStatuses(
    ) {
        return ResponseEntity.ok().body(clientStatusService.getClientStatus());
    }
}
