package com.mcnasimba.msvc_accounts.dtos.clients;

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
public class ClientResponseDTO {
    @Null
    @JsonProperty("labels_id")
    Long idClient = null;

    ClientMicroserviceDTO[] clients;

    @JsonProperty("idAccount")
    Long idAccount;
}
