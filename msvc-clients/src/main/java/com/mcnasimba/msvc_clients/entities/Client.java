package com.mcnasimba.msvc_clients.entities;

import com.mcnasimba.msvc_clients.dtos.accounts.AccountDTO;
import com.mcnasimba.msvc_clients.enums.ClientState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("clients")
@SuperBuilder
public class Client{
    @Id
    private Long idClient;
    private Long idPerson;
    private String password;
    private String clientState;
    private AccountDTO[] accounts;
}


