package com.mcnasimba.msvc_clients.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitAccountsChannel {

    public static final String ACCOUNTS_EXCHANGE = "accounts.pubs";
    public static final String ACCOUNTS_ROUTING_KEY = "accounts.pubs.notify";
    public static final String ACCOUNTS_QUEUE = "accounts.notify.subs";

    @Bean
    public Queue accountsSubsQueue() {
        return new Queue(ACCOUNTS_QUEUE);
    }

    @Bean
    public DirectExchange accountsExchange() {
        return new DirectExchange(ACCOUNTS_EXCHANGE);
    }

    @Bean
    public Binding accountsBinding(DirectExchange accountsExchange) {
        return BindingBuilder.bind(accountsSubsQueue()).to(accountsExchange).with(ACCOUNTS_ROUTING_KEY);
    }
}