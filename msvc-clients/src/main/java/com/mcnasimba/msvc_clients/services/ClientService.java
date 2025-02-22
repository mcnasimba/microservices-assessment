package com.mcnasimba.msvc_clients.services;

import com.mcnasimba.msvc_clients.dtos.ClientDTO;
import com.mcnasimba.msvc_clients.entities.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientService {

    public Flux<ClientDTO> getAllClients();
    public Mono<Client> getClientById(String idClient);
    public Mono<Client> createClient(Client client);
    public Mono<Void> deleteClient(String id);

}
