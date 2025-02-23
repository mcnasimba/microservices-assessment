package com.mcnasimba.msvc_accounts.dtos;

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
public class CreateMovementDTO {

    @NotNull
    @NotBlank
    private String accountNumber;

    @NotNull
    @NotBlank
    private Float amount;

}
