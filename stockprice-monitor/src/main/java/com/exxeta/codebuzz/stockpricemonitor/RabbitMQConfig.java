package com.exxeta.codebuzz.stockpricemonitor;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory.ConfirmType;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.support.RetryTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
class RabbitMQConfig {

    @Bean
    FanoutExchange fanout() {
        return new FanoutExchange("stockPriceUpdate");
    }

    @Bean
    FanoutExchange fanoutV2() {
        return new FanoutExchange("stockPriceUpdateV2");
    }

    @Bean
    Jackson2JsonMessageConverter messageConverter() {
        ObjectMapper mapper = new ObjectMapper();
        return new Jackson2JsonMessageConverter(mapper);
    }

    // @Bean
    // RabbitTemplate rabbitTemplate(final CachingConnectionFactory connectionFactory) {
    //     RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    //     rabbitTemplate.setMessageConverter(messageConverter());
    //     return rabbitTemplate;
    // }
    
    @Bean
    RabbitTemplate rabbitTemplate(final CachingConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        connectionFactory.setPublisherConfirmType(ConfirmType.CORRELATED);
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                System.out.println("Message published successfully: " + (correlationData != null ? correlationData.getId() : "null"));
            } else {
                System.err.println("Message publish failed: " + (correlationData != null ? correlationData.getId() : "null") + ", cause: " + cause);
            }
        });

        return rabbitTemplate;
    }
}
