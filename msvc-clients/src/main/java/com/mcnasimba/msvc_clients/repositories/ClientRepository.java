package com.mcnasimba.msvc_clients.repositories;

import com.mcnasimba.msvc_clients.entities.Client;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ClientRepository extends ReactiveCrudRepository<Client, Long> {

}
