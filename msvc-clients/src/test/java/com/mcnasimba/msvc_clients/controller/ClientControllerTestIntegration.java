package com.mcnasimba.msvc_clients.controller;

import com.mcnasimba.msvc_clients.dtos.ClientDTO;
import com.mcnasimba.msvc_clients.repositories.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.test.StepVerifier;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientControllerTestIntegration {
    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private PersonRepository personRepository;

    @Test
    void testGetAllClients() {
        webTestClient.get()
                .uri("/clients")
                .accept(MediaType.ALL)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ClientDTO.class)
                .hasSize(2);
    }

    @Test
    public void testConnection() {
        StepVerifier.create(personRepository.count())
                .expectNextCount(1)
                .verifyComplete();
    }
}
