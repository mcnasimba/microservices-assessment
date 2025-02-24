package com.mcnasimba.msvc_clients.controller;

import com.mcnasimba.msvc_clients.dtos.ClientDTO;
import com.mcnasimba.msvc_clients.dtos.CreateClientDTO;
import com.mcnasimba.msvc_clients.dtos.accounts.AccountMicroserviceDTO;
import com.mcnasimba.msvc_clients.entities.Client;
import com.mcnasimba.msvc_clients.services.AccountService;
import com.mcnasimba.msvc_clients.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/clients")
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;

    private final AccountService accountService;

    @GetMapping()
    public Flux<ClientDTO> getAllClients(){
        return clientService.getAllClients();
    }

    @GetMapping("/{idClient}")
    public Mono<ClientDTO> getClientById(@PathVariable Long idClient){
        return this.clientService.getClientById(idClient);
    }

    @GetMapping("/client-account/{idClient}")
    public Flux<AccountMicroserviceDTO[]> getAccountsByClientId(@PathVariable("idClient") Long idClient) {
        return this.accountService.findAllAccountByIdClient(idClient);
    }

    @PostMapping()
    public Mono<ClientDTO> createClient(@RequestBody CreateClientDTO  createClientDTO){
        return  this.clientService.createClient(createClientDTO);
    }

    @DeleteMapping("/{idClient}")
    public Mono<Void> deleteClient(@PathVariable Long idClient){
        return this.clientService.deleteClient(idClient);
    }
}
