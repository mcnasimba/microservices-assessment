package com.mcnasimba.msvc_accounts.services;

import com.mcnasimba.msvc_accounts.dtos.AccountDTO;
import com.mcnasimba.msvc_accounts.entities.Account;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountService {
    public Flux<AccountDTO> getAllAccounts();
    public Mono<AccountDTO> getAccountsById(Long idAccount);
    public Mono<AccountDTO> createAccount(Account account);
    public Mono<Void> deleteAccount(Long idAccount);
}
