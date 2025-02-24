package com.mcnasimba.msvc_accounts.services.implementation;

import com.mcnasimba.msvc_accounts.dtos.CreateMovementDTO;
import com.mcnasimba.msvc_accounts.dtos.MovementDTO;
import com.mcnasimba.msvc_accounts.dtos.UpdateBalanceAccount;
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
        return this.accountRepository.findByAccountNumber(createMovementDTO.getAccountNumber())
                .switchIfEmpty(Mono.error(new Exception("Cuenta no encontrada")))
                .flatMap(account->{
                    Float currentBalance = account.getInitialBalance();
                    Float movementAmount = createMovementDTO.getAmount();
                    float newBalance = currentBalance + movementAmount;

                    if(newBalance==0)
                        return Mono.error(new Exception("Transaction amount must be diferent to zero"));

                    if(newBalance<0)
                        return Mono.error(new Exception("Amount not allowed"));

                    String movementType = newBalance>0?"Retreat":"Deposit";

                    account.setInitialBalance(newBalance);

                    UpdateBalanceAccount updateAccount = UpdateBalanceAccount
                            .builder()
                            .initialBalance(newBalance)
                            .build();

                    return accountService.updateAccount(createMovementDTO.getAccountNumber(), updateAccount)
                            .flatMap(updatedAccount -> {
                                Movement movementCreate = Movement.builder()
                                        .idAccount(account.getIdAccount())
                                        .movementType(movementType)
                                        .movementState(true)
                                        .balance(newBalance)
                                        .amount(createMovementDTO.getAmount())
                                        .build();
                                   //Movement movementCreate = modelMapper.map(createMovementDTO, Movement.class);
                                   return movementRepository.save(movementCreate);
                            });
                }).map(s -> modelMapper.map(s, MovementDTO.class));
    }

    @Override
    public Mono<Void> deleteMovement(Long idMovement) {
        return this.movementRepository.deleteById(idMovement);
    }
}
