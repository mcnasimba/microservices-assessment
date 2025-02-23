package com.mcnasimba.msvc_accounts.services;

import com.mcnasimba.msvc_accounts.dtos.AccountStateDTO;

import java.time.LocalDate;

public interface ReportService {
    public AccountStateDTO getAccountState (LocalDate startDate, LocalDate endTime);
}
