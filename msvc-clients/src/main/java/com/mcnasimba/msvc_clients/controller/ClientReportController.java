package com.mcnasimba.msvc_clients.controller;

import com.mcnasimba.msvc_clients.dtos.ClientDTO;
import com.mcnasimba.msvc_clients.dtos.accounts.AccountMicroserviceDTO;
import com.mcnasimba.msvc_clients.dtos.accounts.AccountStateDTO;
import com.mcnasimba.msvc_clients.dtos.accounts.ClientStateDTO;
import com.mcnasimba.msvc_clients.services.AccountService;
import com.mcnasimba.msvc_clients.services.ClientService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RestController
@RequestMapping("/reports")
@AllArgsConstructor
public class ClientReportController {
    private final AccountService accountService;
    private final ClientService clientService;

    private final ModelMapper modelMapper;

    @GetMapping("/{idClient}")
    public Mono<ClientStateDTO> getStateAccountByIdClient(@PathVariable("idClient") Long idClient,
                                                          @RequestParam("minDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate minDate,
                                                          @RequestParam("maxDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate maxDate) {
        return clientService.getClientById(idClient)
                .flatMap(client -> {
                    ClientStateDTO clientStateDTO = modelMapper.map(client, ClientStateDTO.class);
                    return accountService.getReportByIdClient(idClient, minDate, maxDate)
                            .collectList()
                            .map(reportList -> {
                                ClientStateDTO stateClient = modelMapper.map(client, ClientStateDTO.class);
                                stateClient.setMovements(reportList);

                                return stateClient;
                            });
                });


    }

}
