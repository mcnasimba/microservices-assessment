package com.mcnasimba.msvc_clients.services;

import com.mcnasimba.msvc_clients.dtos.ClientDTO;
import com.mcnasimba.msvc_clients.dtos.CreateClientDTO;
import com.mcnasimba.msvc_clients.entities.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientService {

    public Flux<ClientDTO> getAllClients();
    public Mono<ClientDTO> getClientById(Long idClient);
    public Mono<ClientDTO> createClient(CreateClientDTO  createClientDTO);
    public Mono<Void> deleteClient(Long idClient);

}
