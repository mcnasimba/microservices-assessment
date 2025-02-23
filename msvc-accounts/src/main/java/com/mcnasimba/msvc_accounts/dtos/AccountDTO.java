package com.mcnasimba.msvc_accounts.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDTO {

    @NotNull(message = "Insert a clientId to continue")
    @NotBlank
    private Long idClient;

    @NotNull(message = "Insert an account number to continue")
    @NotBlank
    private String accountNumber;

    @NotNull
    @NotBlank
    private String accountType;

    @NotNull
    @NotBlank
    @Min(value = 1, message = "the initial balance must be greater than 0")
    private Float initialBalance;
}
