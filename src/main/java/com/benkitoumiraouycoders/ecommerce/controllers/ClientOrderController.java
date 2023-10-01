package com.benkitoumiraouycoders.ecommerce.controllers;

import com.benkitoumiraouycoders.ecommerce.dtos.ClientOrderDto;
import com.benkitoumiraouycoders.ecommerce.services.inter.ClientOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/clientOrders")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:58213", allowCredentials = "true")
public class ClientOrderController {
    private ClientOrderService clientOrderService;

    @GetMapping
    public ResponseEntity<List<ClientOrderDto>> getClientOrdersByQuery(@RequestParam(name = "orderId", required = false) Long orderId,
                                                                       @RequestParam(name = "clientId", required = false) Long clientId,
                                                                       @RequestParam(name = "dateCreation", required = false) LocalDateTime dateCreation,
                                                                       @RequestParam(name = "dateUpdate", required = false) LocalDateTime dateUpdate,
                                                                       @RequestParam(name = "orderStatusId", required = false) Long orderStatusId
    ) {
        return ResponseEntity.ok().body(clientOrderService.getClientOrdersByQuery(orderId, clientId, orderStatusId, dateCreation,
                dateUpdate));
    }

    @GetMapping("/{clientOrderId}")
    public ResponseEntity<ClientOrderDto> getClientOrderById(@PathVariable Long clientOrderId) {
        return ResponseEntity.ok().body(clientOrderService.getClientOrderById(clientOrderId));
    }

    @PostMapping
    public ResponseEntity<ClientOrderDto> addClientOrder(
            @RequestBody ClientOrderDto clientOrderDto) throws IOException {
        return ResponseEntity.ok().body(clientOrderService.addClientOrder(clientOrderDto));
    }


    @PutMapping("/{clientOrderId}")
    public ResponseEntity<ClientOrderDto> updateClientOrder(@PathVariable Long clientOrderId, @RequestBody ClientOrderDto clientOrderDto) {
        return ResponseEntity.ok().body(clientOrderService.updateClientOrder(clientOrderId, clientOrderDto));
    }

    @DeleteMapping("/{clientOrderId}")
    public ResponseEntity<?> deleteClientOrderById(@PathVariable Long clientOrderId) {
        return ResponseEntity.ok().body(clientOrderService.deleteClientOrderById(clientOrderId));
    }
}
