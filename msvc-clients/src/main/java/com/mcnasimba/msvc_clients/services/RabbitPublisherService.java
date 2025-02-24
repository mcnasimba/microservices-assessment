package com.mcnasimba.msvc_clients.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcnasimba.msvc_clients.dtos.accounts.MessagingDTO;
import com.mcnasimba.msvc_clients.messaging.RabbitAccountsChannel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitPublisherService {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public void sendMessage(MessagingDTO<?> messageDto) {
        try {
            String messageJson = objectMapper.writeValueAsString(messageDto);
            log.info("Send Message: {}", messageJson);
            rabbitTemplate.convertAndSend(
                    RabbitAccountsChannel.ACCOUNTS_EXCHANGE,
                    RabbitAccountsChannel.ACCOUNTS_ROUTING_KEY,
                    messageJson);
        } catch (Exception e) {
            log.error("Error to send message: {}", e.getMessage());
        }
    }
}
