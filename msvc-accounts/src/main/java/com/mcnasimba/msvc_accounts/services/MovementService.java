package com.mcnasimba.msvc_accounts.services;

import com.mcnasimba.msvc_accounts.dtos.CreateMovementDTO;
import com.mcnasimba.msvc_accounts.dtos.MovementDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovementService {
    public Flux<MovementDTO> getAllMovements();
    public Mono<MovementDTO> getMovementById(Long idMovement);
    public Mono<MovementDTO> createMovement(CreateMovementDTO createMovementDTO);
    public Mono<Void> deleteMovement(Long idMovement);
}
