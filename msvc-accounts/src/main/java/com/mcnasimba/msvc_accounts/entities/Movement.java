package com.mcnasimba.msvc_accounts.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("movimientos")
public class Movement {

    @Id
    private UUID movimientoId=UUID.randomUUID();

    private Long cuentaId;

    private LocalDate fecha; //format: yyyy-mm-dd

    private String tipoMovimiento;

    private Long valor;

    private Long saldo;
}
