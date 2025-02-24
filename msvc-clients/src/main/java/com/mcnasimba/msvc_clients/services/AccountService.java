package com.mcnasimba.msvc_clients.services;

import com.mcnasimba.msvc_clients.dtos.accounts.AccountMicroserviceDTO;
import com.mcnasimba.msvc_clients.dtos.accounts.AccountStateDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface AccountService {

    Flux<AccountMicroserviceDTO[]> findAllAccountByIdClient(Long idClient);

    Flux<AccountStateDTO> getReportByIdClient(Long idClient, LocalDate minDate, LocalDate maxDate);
}
