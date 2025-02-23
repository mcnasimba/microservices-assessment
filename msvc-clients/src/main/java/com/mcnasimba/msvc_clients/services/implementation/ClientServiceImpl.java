package com.mcnasimba.msvc_clients.services.implementation;

import com.mcnasimba.msvc_clients.dtos.ClientDTO;
import com.mcnasimba.msvc_clients.dtos.CreateClientDTO;
import com.mcnasimba.msvc_clients.entities.Client;
import com.mcnasimba.msvc_clients.entities.Person;
import com.mcnasimba.msvc_clients.repositories.ClientRepository;
import com.mcnasimba.msvc_clients.repositories.PersonRepository;
import com.mcnasimba.msvc_clients.services.ClientService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    private final PersonRepository personRepository;

    private final ModelMapper modelMapper;
    @Override
    public Flux<ClientDTO> getAllClients() {
        return clientRepository.findAll()
                .flatMap(client->personRepository.findById(client.getIdPerson())
                        .map(person -> new ClientDTO(client, person)));
    }

    @Override
    public Mono<ClientDTO> getClientById(Long idClient) {
        return clientRepository
                .findById(idClient)
                .flatMap(client-> personRepository.findById(client.getIdPerson())
                .map(person->new ClientDTO(client, person)));
    }

    @Override
    public Mono<ClientDTO> createClient(CreateClientDTO  createClientDTO) {

        Person personCreate = Person.builder()
                .fullName(createClientDTO.getFullName())
                .age(createClientDTO.getAge())
                .gender(createClientDTO.getGender())
                .phone(createClientDTO.getPhone())
                .address(createClientDTO.getAddress())
                .identification(createClientDTO.getIdentification())
                .build();

        return personRepository
                .save(personCreate)
                .flatMap(personSaved -> {
                    Client clientCreate = Client.builder()
                            .idPerson(personSaved.getIdPerson())
                            .password(createClientDTO.getPassword())
                            .clientState(createClientDTO.getClientState())
                            .build();
                    return clientRepository.save(clientCreate);
                }).map(clientSaved->modelMapper.map(clientSaved, ClientDTO.class));
    }

    @Override
    public Mono<Void> deleteClient(Long idClient) {
        return clientRepository.findById(idClient)
                .switchIfEmpty(Mono.error(new RuntimeException("Client not found")))
                .flatMap(client ->
                        clientRepository.delete(client)
                                .then(personRepository.deleteById(client.getIdPerson()))
                );
    }
}
