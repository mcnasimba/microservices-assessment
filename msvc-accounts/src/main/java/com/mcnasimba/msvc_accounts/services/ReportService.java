package com.mcnasimba.msvc_accounts.services;

import com.mcnasimba.msvc_accounts.dtos.AccountStateDTO;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

public interface ReportService {
    public Flux<AccountStateDTO> getAccountState (Long idClient, LocalDate minDate, LocalDate maxDate);
}
