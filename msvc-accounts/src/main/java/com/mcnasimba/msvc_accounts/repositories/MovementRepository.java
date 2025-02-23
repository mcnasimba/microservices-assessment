package com.mcnasimba.msvc_accounts.repositories;

import com.mcnasimba.msvc_accounts.entities.Movement;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MovementRepository extends ReactiveCrudRepository<Movement, Long> {
}
