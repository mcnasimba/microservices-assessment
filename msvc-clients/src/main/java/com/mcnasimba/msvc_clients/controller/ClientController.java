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
    public Mono<ResponseEntity<Flux<ClientDTO>>> getAllClients(){
        Flux<ClientDTO> clients = clientService.getAllClients();
        return Mono.just(ResponseEntity.ok(clients));
    }

    @GetMapping("/{idClient}")
    public Mono<ResponseEntity<ClientDTO>> getClientById(@PathVariable Long idClient){
        return clientService.getClientById(idClient)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/client-account/{idClient}")
    public Mono<ResponseEntity<Flux<AccountMicroserviceDTO[]>>> getAccountsByClientId(@PathVariable("idClient") Long idClient) {
        Flux<AccountMicroserviceDTO[]> accounts = accountService.findAllAccountByIdClient(idClient);
        return Mono.just(ResponseEntity.ok(accounts));
    }

    @PostMapping()
    public Mono<ResponseEntity<ClientDTO>> createClient(@RequestBody CreateClientDTO createClientDTO){
        return clientService.createClient(createClientDTO)
                .map(createdClient -> ResponseEntity.status(HttpStatus.CREATED).body(createdClient));
    }

    @DeleteMapping("/{idClient}")
    public Mono<ResponseEntity<Void>> deleteClient(@PathVariable Long idClient){
        return clientService.deleteClient(idClient)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }
}
