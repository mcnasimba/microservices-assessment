package com.mcnasimba.msvc_accounts.services;

import com.mcnasimba.msvc_accounts.dtos.MovementDTO;
import com.mcnasimba.msvc_accounts.entities.Account;
import com.mcnasimba.msvc_accounts.entities.Movement;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovementService {
    public Flux<MovementDTO> getAllMovements();
    public Mono<MovementDTO> getMovementById(Long idMovement);
    public Mono<MovementDTO> createMovement(Movement movement);
    public Mono<Void> deleteMovement(Long idMovement);
}
