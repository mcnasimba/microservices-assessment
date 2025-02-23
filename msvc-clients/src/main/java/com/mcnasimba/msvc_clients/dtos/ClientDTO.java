package com.mcnasimba.msvc_clients.dtos;

import com.mcnasimba.msvc_clients.entities.Client;
import com.mcnasimba.msvc_clients.entities.Person;
import com.mcnasimba.msvc_clients.enums.ClientState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    //Client details
    private Long idClient;
    private Long idPerson;
    private String clientState;

    //Person details
    private String fullName;
    private String gender;
    private int age;
    private String identification;
    private String address;
    private String phone;

    public ClientDTO(Client client, Person person) {
        this.idClient = client.getIdClient();
        this.idPerson = person.getIdPerson();
        this.clientState = client.getClientState();
        this.fullName = person.getFullName();
        this.gender = person.getGender();
        this.age = person.getAge();
        this.identification = person.getIdentification();
        this.address = person.getAddress();
        this.phone = person.getPhone();
    }
}
