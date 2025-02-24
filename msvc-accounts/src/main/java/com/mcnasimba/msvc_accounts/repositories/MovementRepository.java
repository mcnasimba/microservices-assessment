package com.mcnasimba.msvc_accounts.repositories;

import com.mcnasimba.msvc_accounts.dtos.MovementDTO;
import com.mcnasimba.msvc_accounts.entities.Movement;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovementRepository extends ReactiveCrudRepository<Movement, Long> {
    Flux<Movement> findByIdAccount(Long idAccount);
}
