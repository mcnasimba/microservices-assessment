package com.mcnasimba.msvc_accounts.services.implementation;

import com.mcnasimba.msvc_accounts.dtos.MovementDTO;
import com.mcnasimba.msvc_accounts.entities.Movement;
import com.mcnasimba.msvc_accounts.repositories.AccountRepository;
import com.mcnasimba.msvc_accounts.repositories.MovementRepository;
import com.mcnasimba.msvc_accounts.services.AccountService;
import com.mcnasimba.msvc_accounts.services.MovementService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class MovementServiceImpl implements MovementService {

    private final MovementRepository movementRepository;
    private final AccountService accountService;
private final ModelMapper modelMapper;
    @Override
    public Flux<MovementDTO> getAllMovements() {
        return this.movementRepository
                .findAll()
                .map(movement->modelMapper.map(movement, MovementDTO.class));
    }

    @Override
    public Mono<MovementDTO> getMovementById(Long idMovement) {
        return this.movementRepository
                .findById(idMovement)
                .map(movement->modelMapper.map(movement, MovementDTO.class));
    }

    @Override
    public Mono<MovementDTO> createMovement(Movement movement) {
        return this.movementRepository
                .save(movement)
                .map(movementCreated->modelMapper.map(movementCreated, MovementDTO.class));
    }

    @Override
    public Mono<Void> deleteMovement(Long idMovement) {
        return this.movementRepository.deleteById(idMovement);
    }
}
