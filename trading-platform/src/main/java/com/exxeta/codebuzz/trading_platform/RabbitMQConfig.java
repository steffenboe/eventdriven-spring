package com.exxeta.codebuzz.trading_platform;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class RabbitMQConfig {

    // @Bean
    // public FanoutExchange fanoutExchange() {
    //     return new FanoutExchange("stockPriceUpdate");
    // }

    // @Bean
    // public Queue stockPriceQueue() {
    //     return new Queue("tradingPlattformStockPriceUpdate", false);
    // }

    // @Bean
    // public Binding stockPriceBindingV2(Queue stockPriceQueue, FanoutExchange fanoutExchange) {
    //     return BindingBuilder.bind(stockPriceQueueV2).to(fanoutExchange);
    // }

    // @Bean
    // public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
    //     final var rabbitTemplate = new RabbitTemplate(connectionFactory);
    //     rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
    //     return rabbitTemplate;
    // }

    // @Bean
    // public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
    //     return new Jackson2JsonMessageConverter();
    // }

    // @Bean
    // public Queue deadLetterQueue() {
    //     return new Queue("tradingPlattformStockPriceUpdateV2.dlq", false);
    // }

    // @Bean
    // public Binding deadLetterBinding(Queue deadLetterQueue) {
    //     return BindingBuilder
    //         .bind(deadLetterQueue)
    //         .to(dlx())
    //         .with("");
    // }

    // @Bean
    // DirectExchange dlx() {
    //     return new DirectExchange("tradingPlatformStockPriceUpdate.dlx");
    // }

    // @Bean
    // public Queue stockPriceQueueV2() {
    //     return QueueBuilder
    //         .nonDurable("tradingPlattformStockPriceUpdateV2")
    //         .withArgument("x-dead-letter-exchange", "tradingPlatformStockPriceUpdate.dlx")
    //         .withArgument("x-dead-letter-routing-key", "")
    //         .build();
    // }
}
