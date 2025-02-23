package com.mcnasimba.msvc_accounts.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovementDTO {

//    @NotNull(message = "Insert an account id to continue")
//    @NotBlank
//    private Long idAccount;

//    @NotNull
//    @NotBlank
//    private LocalDate transactionDate;

    @NotNull
    @NotBlank
    private String accountNumber;

    @NotNull
    @NotBlank
    private String movementType;

    @NotNull
    @NotBlank
    private Long amount;

//    @NotNull
//    @NotBlank
//    private Long balance;

}
