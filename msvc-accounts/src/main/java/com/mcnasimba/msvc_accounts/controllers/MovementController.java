package com.mcnasimba.msvc_accounts.controllers;

import com.mcnasimba.msvc_accounts.dtos.CreateMovementDTO;
import com.mcnasimba.msvc_accounts.dtos.MovementDTO;
import com.mcnasimba.msvc_accounts.entities.Movement;
import com.mcnasimba.msvc_accounts.services.MovementService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/movements")
@AllArgsConstructor
public class MovementController {

    private final MovementService movementService;

    @GetMapping()
    public Mono<ResponseEntity<Flux<MovementDTO>>> getAllMovements() {
        Flux<MovementDTO> movements = movementService.getAllMovements();
        return Mono.just(ResponseEntity.ok(movements));
    }

    @GetMapping("/{idMovement}")
    public Mono<ResponseEntity<MovementDTO>> getMovementById(@PathVariable Long idMovement) {
        return movementService.getMovementById(idMovement)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<MovementDTO>> createMovement(@RequestBody CreateMovementDTO createMovementDTO) {
        return movementService.createMovement(createMovementDTO)
                .map(movement -> ResponseEntity.status(HttpStatus.CREATED).body(movement));
    }

    @DeleteMapping("/{idMovement}")
    public Mono<ResponseEntity<Void>> deleteMovement(@PathVariable Long idMovement) {
        return movementService.deleteMovement(idMovement)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }
}
