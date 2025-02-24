package com.mcnasimba.msvc_accounts.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcnasimba.msvc_accounts.dtos.MessagingDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitMessage {
    private final ObjectMapper objectMapper;

    public <T> Message parseMessage(MessagingDTO<T> messageDto) throws IOException {

        if (messageDto == null) {
            return null;
        }

        if (messageDto.getPayload() == null) {
            return null;
        }

        MessageProperties properties = new MessageProperties();

        properties.setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT);

        return MessageBuilder
                .withBody(objectMapper.writeValueAsBytes(messageDto))
                .andProperties(properties)
                .build();
    }
}
