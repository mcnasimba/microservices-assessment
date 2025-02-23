package com.mcnasimba.msvc_accounts.entities;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("cuentas")
public class Account {

    @Id
    private Long cuentaId;

    private Long clienteId;

    private Long numeroCuenta;

    private String tipoCuenta;

    private Long SaldoInicial;

    private String estado;

}
