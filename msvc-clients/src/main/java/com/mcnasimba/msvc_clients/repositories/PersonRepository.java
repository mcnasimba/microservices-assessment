package com.mcnasimba.msvc_clients.repositories;

import com.mcnasimba.msvc_clients.entities.Person;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PersonRepository extends ReactiveCrudRepository<Person, Long> {
}
