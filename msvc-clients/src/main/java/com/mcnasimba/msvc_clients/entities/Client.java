package com.mcnasimba.msvc_clients.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("clientes")
public class Client extends Person{
    private Long clienteId;
    private String password;
    private String estado;
}


