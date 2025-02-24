package com.mcnasimba.msvc_clients.dtos.accounts;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponseDTO {
    @Null
    @JsonProperty("accountNumber")
    String accountNumber = null;

    AccountMicroserviceDTO[] accounts;

    @JsonProperty("idClient")
    Long idClient;
}
