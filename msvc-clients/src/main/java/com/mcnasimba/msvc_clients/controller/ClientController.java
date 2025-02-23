package com.mcnasimba.msvc_clients.controller;

import com.mcnasimba.msvc_clients.dtos.ClientDTO;
import com.mcnasimba.msvc_clients.entities.Client;
import com.mcnasimba.msvc_clients.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/clients")
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping()
    public Flux<ClientDTO> getAllClients(){
        return clientService.getAllClients();
    }

    @GetMapping("/{idClient}")
    public Mono<Client> getClientById(@PathVariable Long clientId){
        return this.clientService.getClientById(clientId);
    }

    @PostMapping()
    public Mono<Client> createClient(@RequestBody Client client){
        return  this.clientService.createClient(client);
    }

    @DeleteMapping("/{idClient}")
    public Mono<Void> deleteClient(@PathVariable Long idClient){
        return this.clientService.deleteClient(idClient);
    }
}
