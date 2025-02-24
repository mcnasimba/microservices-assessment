package com.mcnasimba.msvc_accounts.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitClientsChannel {

    public static final String CLIENTS_EXCHANGE = "clients.pubs";
    public static final String CLIENTS_ROUTING_KEY = "clients.pubs.notify";
    public static final String CLIENTS_QUEUE = "clients.notify.subs";

    @Bean
    public Queue clientsSubsQueue() {
        return new Queue(CLIENTS_QUEUE);
    }

    @Bean
    public DirectExchange clientsExchange() {
        return new DirectExchange(CLIENTS_EXCHANGE);
    }

    @Bean
    public Binding clientsBinding(DirectExchange clientsExchange) {
        return BindingBuilder.bind(clientsSubsQueue()).to(clientsExchange).with(CLIENTS_ROUTING_KEY);
    }
}
