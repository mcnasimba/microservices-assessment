package com.mcnasimba.msvc_accounts.services;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mcnasimba.msvc_accounts.dtos.clients.ClientMicroserviceDTO;
import jakarta.validation.constraints.Null;
import reactor.core.publisher.Mono;


public interface ClientService {

    Mono<ClientMicroserviceDTO> findClientByAccountNumber(String accountNumber);
}
