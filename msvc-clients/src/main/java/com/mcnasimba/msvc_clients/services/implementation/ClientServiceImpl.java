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
    public Mono<ClientDTO> createClient(CreateClientDTO createClientDTO) {
        Person person = toPerson(createClientDTO);
        return personRepository.save(person)
                .flatMap(savedPerson -> clientRepository.save(toClient(createClientDTO, savedPerson.getIdPerson())))
                .map(savedClient -> modelMapper.map(savedClient, ClientDTO.class));
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

    /* ---- Helpers ---- */
    private Person toPerson(CreateClientDTO dto) {
        return Person.builder()
                .fullName(dto.getFullName())
                .age(dto.getAge())
                .gender(dto.getGender())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .identification(dto.getIdentification())
                .build();
    }

    private Client toClient(CreateClientDTO dto, Long idPerson) {
        return Client.builder()
                .idPerson(idPerson)
                .password(dto.getPassword())
                .clientState(dto.getClientState())
                .build();
    }
}
