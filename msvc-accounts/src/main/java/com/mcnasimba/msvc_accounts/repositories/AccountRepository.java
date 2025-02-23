package com.mcnasimba.msvc_accounts.repositories;

import com.mcnasimba.msvc_accounts.entities.Account;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface AccountRepository extends ReactiveCrudRepository<Account, Long> {
}
