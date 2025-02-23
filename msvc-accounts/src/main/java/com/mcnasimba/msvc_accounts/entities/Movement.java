package com.mcnasimba.msvc_accounts.entities;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("movements")
@Builder
public class Movement {

    @Id
    private Long idMovement;

    private Long idAccount;

    private LocalDate transactionDate; //format: yyyy-mm-dd

    private String movementType;

    private Boolean movementState;

    private Float amount;

    private Float balance;
}
