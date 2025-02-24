package com.mcnasimba.msvc_accounts.controllers;

import com.mcnasimba.msvc_accounts.dtos.AccountDTO;
import com.mcnasimba.msvc_accounts.entities.Account;
import com.mcnasimba.msvc_accounts.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping()
    public Mono<ResponseEntity<Flux<AccountDTO>>>getAllAccounts(){
        Flux<AccountDTO> accounts = this.accountService.getAllAccounts();
        return Mono.just(ResponseEntity.ok(accounts));
    }

    @GetMapping("/{idAccount}")
    public Mono<ResponseEntity<AccountDTO>> getAccountById(@PathVariable Long idAccount){
        return this.accountService.getAccountsById(idAccount)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/client-account/{idClient}")
    public Mono<ResponseEntity<Flux<AccountDTO>>> getAccountsByClientId(@PathVariable Long idClient){
        Flux<AccountDTO> accounts = this.accountService.getAccountsByIdClient(idClient);
        return Mono.just(ResponseEntity.ok(accounts));
    }

    @GetMapping("/numberAccount/{accountNumber}")
    public Mono<ResponseEntity<AccountDTO>> getAccountByAccountNumber(@PathVariable String accountNumber){
        return this.accountService.getAccountByAccountNumber(accountNumber)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public Mono<ResponseEntity<AccountDTO>> createAccount(@RequestBody Account account){
        return this.accountService.createAccount(account)
                .map(createdAccount -> ResponseEntity.status(HttpStatus.CREATED).body(createdAccount));
    }

    @DeleteMapping("/{idAccount}")
    public Mono<ResponseEntity<Void>> deleteAccount(@PathVariable Long idAccount){
        return this.accountService.deleteAccount(idAccount)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }
}
