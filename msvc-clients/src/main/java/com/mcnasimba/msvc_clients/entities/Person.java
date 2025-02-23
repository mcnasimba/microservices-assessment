package com.mcnasimba.msvc_clients.entities;

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
@Table("persons")
@SuperBuilder
public class Person {
    @Id
    private Long idPerson;
    private String fullName;
    private String gender;
    private int age;
    private String identification;
    private String address;
    private String phone;

}
