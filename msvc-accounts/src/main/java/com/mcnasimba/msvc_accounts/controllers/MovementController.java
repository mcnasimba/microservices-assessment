package com.mcnasimba.msvc_accounts.controllers;

import com.mcnasimba.msvc_accounts.dtos.MovementDTO;
import com.mcnasimba.msvc_accounts.entities.Movement;
import com.mcnasimba.msvc_accounts.services.MovementService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/movements")
@AllArgsConstructor
public class MovementController {

    private final MovementService movementService;

    @GetMapping()
    public Flux<MovementDTO> getAllMovements(){
        return this.movementService.getAllMovements();
    }

    @GetMapping("/{idMovement}")
    public Mono<MovementDTO> getMovementById(@PathVariable Long idMovement){
        return  this.movementService.getMovementById(idMovement);
    }

    @PostMapping
    public Mono<MovementDTO> createMovement(@RequestBody Movement movement){
        return this.movementService.createMovement(movement);
    }

    @DeleteMapping("/{idMovement}")
    public Mono<Void> deleteMovement(@PathVariable Long idMovement){
        return this.movementService.deleteMovement(idMovement);
    }
}
