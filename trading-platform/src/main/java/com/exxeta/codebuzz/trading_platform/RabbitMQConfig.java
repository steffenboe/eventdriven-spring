package com.exxeta.codebuzz.trading_platform;

import java.util.HashMap;
import java.util.List;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rabbitmq.client.AMQP.Exchange;

@Configuration
class RabbitMQConfig {

    // @Bean
    // public Queue stockPriceQueue() {
    //     return new Queue("tradingPlattformStockPriceUpdate", false);
    // }

    // @Bean
    // public FanoutExchange fanoutExchange() {
    //     return new FanoutExchange("stockPriceUpdate");
    // }

    // @Bean
    // public Binding stockPriceBinding(Queue stockPriceQueue, FanoutExchange fanout) {
    //     return BindingBuilder.bind(stockPriceQueue).to(fanout);
    // }

    // @Bean
    // public FanoutExchange fanoutExchangeV2() {
    //     return new FanoutExchange("stockPriceUpdateV2");
    // }

    // @Bean
    // public Queue stockPriceQueueV2() {
    //     return new Queue("tradingPlattformStockPriceUpdateV2", false);
    // }

    // @Bean
    // public Binding stockPriceBindingV2(Queue stockPriceQueueV2, FanoutExchange fanout) {
    //     return BindingBuilder.bind(stockPriceQueueV2).to(fanout);
    // }

    // @Bean
    // public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
    //     final var rabbitTemplate = new RabbitTemplate(connectionFactory);
    //     rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
    //     return rabbitTemplate;
    // }

    // @Bean
    // public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
    //     return new Jackson2JsonMessageConverter();
    // }
}
