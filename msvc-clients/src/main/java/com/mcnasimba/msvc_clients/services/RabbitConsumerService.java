package com.mcnasimba.msvc_clients.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcnasimba.msvc_clients.dtos.accounts.MessagingDTO;
import com.mcnasimba.msvc_clients.messaging.RabbitAccountsChannel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitConsumerService {
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = RabbitAccountsChannel.ACCOUNTS_QUEUE)
    public void receiveMessage(String messageJson) {
        try {
            // Supongamos que el payload es String; si es otro tipo, utiliza el TypeReference adecuado
            MessagingDTO<String> messagingDTO = objectMapper.readValue(messageJson, MessagingDTO.class);
            log.info("Mensaje recibido: UUID: {}, Source: {}, Detail: {}, Payload: {}",
                    messagingDTO.getUuid(), messagingDTO.getSource(),
                    messagingDTO.getDetailType(), messagingDTO.getPayload());
            // Procesa el mensaje según tu lógica de negocio
        } catch (Exception e) {
            log.error("Error al procesar el mensaje: {}", e.getMessage());
        }
    }
}
