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

            MessagingDTO<String> messagingDTO = objectMapper.readValue(messageJson, MessagingDTO.class);
            log.info("Message send: UUID: {}, Source: {}, Detail: {}, Payload: {}",
                    messagingDTO.getUuid(), messagingDTO.getSource(),
                    messagingDTO.getDetailType(), messagingDTO.getPayload());

        } catch (Exception e) {
            log.error("Error Message: {}", e.getMessage());
        }
    }
}
