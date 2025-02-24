package com.mcnasimba.msvc_accounts.dtos.clients;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientRequestDTO {
    @JsonProperty("idClient")
    Long idClient;

    String[] account;

    @JsonProperty("accountNumber")
    String accountNumber;
}
