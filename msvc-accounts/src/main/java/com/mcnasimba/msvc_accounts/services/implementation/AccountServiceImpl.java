package com.mcnasimba.msvc_accounts.services.implementation;

import com.mcnasimba.msvc_accounts.dtos.AccountDTO;
import com.mcnasimba.msvc_accounts.entities.Account;
import com.mcnasimba.msvc_accounts.repositories.AccountRepository;
import com.mcnasimba.msvc_accounts.services.AccountService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;

    @Override
    public Flux<AccountDTO> getAllAccounts() {
        return this.accountRepository
                .findAll()
                .map(account -> modelMapper.map(account, AccountDTO.class));
    }

    @Override
    public Mono<AccountDTO> getAccountsById(Long idAccount) {
        return this.accountRepository
                .findById(idAccount)
                .map(account -> modelMapper.map(account, AccountDTO.class));
    }

    @Override
    public Mono<AccountDTO> createAccount(Account account) {
        return this.accountRepository
                .save(account)
                .map(accountCreated->modelMapper.map(accountCreated, AccountDTO.class));
    }

    @Override
    public Mono<Void> deleteAccount(Long idAccount) {
        return this.accountRepository.deleteById(idAccount);
    }
}
