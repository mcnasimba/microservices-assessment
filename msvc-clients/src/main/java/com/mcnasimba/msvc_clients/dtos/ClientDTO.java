package com.mcnasimba.msvc_clients.dtos;

import com.mcnasimba.msvc_clients.entities.Client;
import com.mcnasimba.msvc_clients.entities.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    private Long clientId;
    //private String password;
    private String estado;

    private String nombre;
    private String genero;
    private int edad;
    private String identificacion;
    private String direccion;
    private String telefono;

    public ClientDTO(Client client, Person person) {
        this.clientId = client.getClienteId();
        //this.password = cliente.getPassword();
        this.estado = client.getEstado();

        this.nombre = person.getNombre();
        this.genero = person.getGenero();
        this.edad = person.getEdad();
        this.identificacion = person.getIdentificacion();
        this.direccion = person.getDireccion();
        this.telefono = person.getTelefono();
    }
}
