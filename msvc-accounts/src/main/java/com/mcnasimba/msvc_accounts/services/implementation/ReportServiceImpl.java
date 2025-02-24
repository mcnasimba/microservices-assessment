package com.mcnasimba.msvc_accounts.services.implementation;

import com.mcnasimba.msvc_accounts.dtos.AccountDTO;
import com.mcnasimba.msvc_accounts.dtos.AccountStateDTO;
import com.mcnasimba.msvc_accounts.dtos.MovementDTO;
import com.mcnasimba.msvc_accounts.services.AccountService;
import com.mcnasimba.msvc_accounts.services.MovementService;
import com.mcnasimba.msvc_accounts.services.ReportService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final AccountService accountService;
    private final MovementService movementService;
    private final ModelMapper modelMapper;

    @Override
    public Flux<AccountStateDTO> getAccountState(Long idClient, LocalDate minDate, LocalDate maxDate) {

        return this.accountService.getAccountsByIdClient(idClient)
                .switchIfEmpty(Flux.error(new Exception("No se han encontrado cuentas del cliente")))
                .flatMap(account -> movementService.getMovementsByAccountNumber(account.getIdAccount())
                        .filter(movement ->  {
                            LocalDate movementDate = movement.getTransactionDate();
                            return !movement.getTransactionDate().isBefore(minDate) && !movementDate.isAfter(maxDate);
                        })
                        .map(movement->mapToAccountStateDTO(account, movement)));
    }

    /* ---- Helpers ---- */
    private AccountStateDTO mapToAccountStateDTO(AccountDTO account, MovementDTO movement) {
        AccountStateDTO dto = modelMapper.map(movement, AccountStateDTO.class);
        dto.setAccountNumber(account.getAccountNumber());
        dto.setAccountType(account.getAccountType());
        dto.setInitialBalance(account.getInitialBalance());
        return dto;
    }
}
