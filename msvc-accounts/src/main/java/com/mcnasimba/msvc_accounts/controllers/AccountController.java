package com.mcnasimba.msvc_accounts.controllers;

import com.mcnasimba.msvc_accounts.dtos.AccountDTO;
import com.mcnasimba.msvc_accounts.entities.Account;
import com.mcnasimba.msvc_accounts.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping()
    public Flux<AccountDTO> getAllAccounts(){
        return  this.accountService.getAllAccounts();
    }

    @GetMapping("/{idAccount}")
    public Mono<AccountDTO> getAccountById(@PathVariable Long idAccount){
        return this.accountService.getAccountsById(idAccount);
    }

    @PostMapping()
    public Mono<AccountDTO> createAccount(@RequestBody Account account){
        return this.accountService.createAccount(account);
    }

    @DeleteMapping("/{idAccount}")
    public Mono<Void> deleteAccount(@PathVariable Long idAccount){
        return this.accountService.deleteAccount(idAccount);
    }

}
