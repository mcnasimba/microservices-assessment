package com.mcnasimba.msvc_clients.services.implementation;

import com.mcnasimba.msvc_clients.dtos.ClientDTO;
import com.mcnasimba.msvc_clients.entities.Client;
import com.mcnasimba.msvc_clients.repositories.ClientRepository;
import com.mcnasimba.msvc_clients.repositories.PersonRepository;
import com.mcnasimba.msvc_clients.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    private final PersonRepository personRepository;

    @Override
    public Flux<ClientDTO> getAllClients() {
        return clientRepository.findAll()
                .flatMap(client->personRepository.findById(client.getPersona_id())
                        .map(person -> new ClientDTO(client, person)));
    }

    @Override
    public Mono<Client> getClientById(String idClient) {
        return clientRepository.findById(idClient);
    }

    @Override
    public Mono<Client> createClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Mono<Void> deleteClient(String idClient) {
        return clientRepository.deleteById(idClient);
    }
}
