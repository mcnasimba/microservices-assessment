package com.mcnasimba.msvc_clients.dtos.accounts;

import com.mcnasimba.msvc_clients.dtos.ClientDTO;
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

    private String accountNumber;

    private String accountType;

    private Float initialBalance;

    private String movementState;

    private Long idMovement;

    private float amount;

    private float balance;

}