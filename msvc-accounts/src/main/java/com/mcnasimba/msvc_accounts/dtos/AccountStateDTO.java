package com.mcnasimba.msvc_accounts.dtos;

import com.mcnasimba.msvc_accounts.entities.Movement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountStateDTO {
    private LocalDate transactionDate;
    private Long accountNumber;
    private String name;
    private Float initialBalance;
    private String accountType;
    private String estado;
    private float transacionAmmount;
    private float balance;

}
