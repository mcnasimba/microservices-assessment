package com.mcnasimba.msvc_clients.dtos.accounts;

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
public class AccountRequestDTO {
    @JsonProperty("accountNumber")
    Long accountNumber;

    AccountDTO[] accounts;

    @JsonProperty("idClient")
    Long idClient;
}
