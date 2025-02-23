package com.mcnasimba.msvc_clients.dtos;

import com.mcnasimba.msvc_clients.enums.ClientState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateClientDTO {

    private String fullName;
    private Long idPerson;
    private String gender;
    private Integer age;
    private String identification;
    private String address;
    private String phone;
    private String password;
    private String clientState;
}
