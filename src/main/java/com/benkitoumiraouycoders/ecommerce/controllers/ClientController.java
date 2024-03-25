package com.benkitoumiraouycoders.ecommerce.controllers;

import com.benkitoumiraouycoders.ecommerce.dtos.ClientDto;
import com.benkitoumiraouycoders.ecommerce.services.inter.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/clients")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:58213", allowCredentials = "true")
public class ClientController {
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientDto>> getClientsByQuery(@RequestParam(name = "clientId", required = false) Long clientId,
                                                             @RequestParam(name = "firstName", required = false) String firstName,
                                                             @RequestParam(name = "lastName", required = false) String lastName,
                                                             @RequestParam(name = "email", required = false) String email,
                                                             @RequestParam(name = "phoneNumber", required = false) String phoneNumber,
                                                             @RequestParam(name = "statusId", required = false) Long statusId
    ) {
        return ResponseEntity.ok().body(clientService.getClientsByQuery(clientId, firstName, lastName,
                email, phoneNumber, statusId));
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable Long clientId) {
        return ResponseEntity.ok().body(clientService.getClientById(clientId));
    }

    @PostMapping
    public ResponseEntity<ClientDto> addClient(
            @RequestBody ClientDto clientDto) throws IOException {
        return ResponseEntity.ok().body(clientService.addClient(clientDto));
    }


    @PutMapping("/{clientId}")
    public ResponseEntity<ClientDto> updateClient
            (@PathVariable Long clientId, @RequestBody ClientDto clientDto) {
        return ResponseEntity.ok().body(clientService.updateClient(clientId, clientDto));
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<?> deleteClientById(@PathVariable Long clientId) {
        return ResponseEntity.ok().body(clientService.deleteClientById(clientId));
    }
}
