package com.mcnasimba.msvc_accounts.services.implementation;

import com.mcnasimba.msvc_accounts.dtos.CreateMovementDTO;
import com.mcnasimba.msvc_accounts.dtos.MovementDTO;
import com.mcnasimba.msvc_accounts.dtos.UpdateBalanceAccount;
import com.mcnasimba.msvc_accounts.entities.Account;
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

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class MovementServiceImpl implements MovementService {

    private final MovementRepository movementRepository;
    private final AccountService accountService;

    private final AccountRepository accountRepository;

    private final ModelMapper modelMapper;

    @Override
    public Flux<MovementDTO> getAllMovements() {
        return this.movementRepository
                .findAll()
                .map(movement -> modelMapper.map(movement, MovementDTO.class));
    }


    @Override
    public Mono<MovementDTO> getMovementById(Long idMovement) {
        return this.movementRepository
                .findById(idMovement)
                .map(movement -> modelMapper.map(movement, MovementDTO.class));
    }

    @Override
    public Flux<MovementDTO> getMovementsByAccountNumber(Long idAccount) {
        return this.movementRepository.findByIdAccount(idAccount)
                .map(movement -> modelMapper.map(movement, MovementDTO.class));
    }

    @Override
    public Mono<MovementDTO> createMovement(CreateMovementDTO createMovementDTO) {
        return accountRepository.findByAccountNumber(createMovementDTO.getAccountNumber())
                .switchIfEmpty(Mono.error(new Exception("Not found account")))
                .flatMap(account -> updateAccountBalance(account, createMovementDTO.getAmount())
                        .flatMap(newBalance ->
                                movementRepository.save(buildMovement(createMovementDTO, account, newBalance))
                        )
                )
                .map(savedMovement -> modelMapper.map(savedMovement, MovementDTO.class));
    }

    @Override
    public Mono<Void> deleteMovement(Long idMovement) {
        return this.movementRepository.deleteById(idMovement);
    }

    /* ---- Helpers ----*/
    private Mono<Float> updateAccountBalance(Account account, Float movementAmount) {
        float newBalance = account.getInitialBalance() + movementAmount;

        if (newBalance == 0)
            return Mono.error(new IllegalArgumentException("Amount must be different to Zero"));

        if (newBalance < 0)
            return Mono.error(new IllegalArgumentException("Balance is not sufficient"));


        account.setInitialBalance(newBalance);
        UpdateBalanceAccount update = UpdateBalanceAccount.builder()
                .initialBalance(newBalance)
                .build();

        return accountService.updateAccount(account.getAccountNumber(), update)
                .map(updated -> newBalance);
    }

    private Movement buildMovement(CreateMovementDTO dto, Account account, float newBalance) {
        String movementType = dto.getAmount() < 0 ? "Retreat" : "Deposit";
        return Movement.builder()
                .idAccount(account.getIdAccount())
                .movementType(movementType)
                .movementState(true)
                .balance(newBalance)
                .amount(dto.getAmount())
                .build();
    }
}
