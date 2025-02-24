package com.mcnasimba.msvc_clients.services.implementation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcnasimba.msvc_clients.dtos.accounts.AccountDTO;
import com.mcnasimba.msvc_clients.dtos.accounts.AccountMicroserviceDTO;
import com.mcnasimba.msvc_clients.dtos.accounts.AccountStateDTO;
import com.mcnasimba.msvc_clients.dtos.accounts.MessagingDTO;
import com.mcnasimba.msvc_clients.entities.Client;
import com.mcnasimba.msvc_clients.messaging.RabbitAccountsChannel;
import com.mcnasimba.msvc_clients.repositories.ClientRepository;
import com.mcnasimba.msvc_clients.services.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.HttpStatus;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final WebClient.Builder webClientBuilder;
    public static final String ACCOUNTS_MICROSERVICE_URL = "http://localhost:3002";

    @Override
    public Flux<AccountMicroserviceDTO[]> findAllAccountByIdClient(Long idClient) {
        WebClient webClient = webClientBuilder.baseUrl(ACCOUNTS_MICROSERVICE_URL).build();
        return webClient.get()
                .uri("/accounts/client-account/{idClient}", idClient)
                .retrieve()
                .bodyToFlux(AccountMicroserviceDTO[].class);
    }

    @Override
    public Flux<AccountStateDTO> getReportByIdClient(Long idClient,
                                                     LocalDate minDate, LocalDate maxDate) {
        WebClient webClient = webClientBuilder.baseUrl(ACCOUNTS_MICROSERVICE_URL).build();
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/reports/{idClient}")
                        .queryParam("minDate", minDate)
                        .queryParam("maxDate", maxDate)
                        .build(idClient))
                .retrieve()
                .bodyToFlux(AccountStateDTO.class);
    }


}
