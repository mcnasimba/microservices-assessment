package com.mcnasimba.msvc_accounts.controllers;

import com.mcnasimba.msvc_accounts.dtos.AccountStateDTO;
import com.mcnasimba.msvc_accounts.dtos.MovementDTO;
import com.mcnasimba.msvc_accounts.services.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RestController
@RequestMapping("/reports")
@AllArgsConstructor
public class ReportController {

    public ReportService reportService;

    @GetMapping("/{idClient}")
    public Mono<ResponseEntity<Flux<AccountStateDTO>>> getMovementById(
            @PathVariable Long idClient,
            @RequestParam("minDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate minDate,
            @RequestParam("maxDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate maxDate) {

        Flux<AccountStateDTO> reportFlux = reportService.getAccountState(idClient, minDate, maxDate);
        return Mono.just(ResponseEntity.ok(reportFlux));
    }
}
