package com.mcnasimba.msvc_clients.client;

import com.mcnasimba.msvc_clients.entities.Client;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ClientTest {
    @Test
    void testClientBuilderAndProperties() {
        Client client = Client.builder()
                .idClient(1L)
                .idPerson(2L)
                .password("password123")
                .clientState("Active")
                .build();

        assertNotNull(client);
        assertEquals(1L, client.getIdClient());
        assertEquals(2L, client.getIdPerson());
        assertEquals("password123", client.getPassword());
        assertEquals("Active", client.getClientState());
    }

}
