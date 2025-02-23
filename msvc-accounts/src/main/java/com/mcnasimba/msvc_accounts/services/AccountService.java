package com.mcnasimba.msvc_accounts.services;

import com.mcnasimba.msvc_accounts.dtos.AccountDTO;
import com.mcnasimba.msvc_accounts.dtos.MovementDTO;
import com.mcnasimba.msvc_accounts.dtos.UpdateBalanceAccount;
import com.mcnasimba.msvc_accounts.entities.Account;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountService {
    public Flux<AccountDTO> getAllAccounts();
    public Mono<AccountDTO> getAccountsById(Long idAccount);
    public Mono<AccountDTO> getAccountByAccountNumber(String accountNumber);
    public Mono<AccountDTO> createAccount(Account account);
    public Mono<AccountDTO> updateAccount(String accountNumber, UpdateBalanceAccount updateBalanceAccount);
    public Mono<Void> deleteAccount(Long idAccount);
}
