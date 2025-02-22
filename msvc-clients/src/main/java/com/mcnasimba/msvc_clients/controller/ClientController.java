package com.mcnasimba.msvc_clients.controller;

import com.mcnasimba.msvc_clients.dtos.ClientDTO;
import com.mcnasimba.msvc_clients.entities.Client;
import com.mcnasimba.msvc_clients.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/clients")
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping()
    public Flux<ClientDTO> getAllClients(){
        return clientService.getAllClients();
    }
}
