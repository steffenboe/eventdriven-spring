package com.exxeta.codebuzz.trading_platform;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;
import org.springframework.retry.interceptor.StatefulRetryOperationsInterceptor;
import org.springframework.retry.interceptor.RetryInterceptorBuilder;

@Configuration
class RabbitMQConfig {

    @Bean
    public FanoutExchange fanoutExchangeV2() {
        return new FanoutExchange("stockPriceUpdateV2");
    }

    @Bean
    public Queue stockPriceQueueV2() {
        return new Queue("tradingPlattformStockPriceUpdateV2", false);
    }

    @Bean
    public Binding stockPriceBindingV2(Queue stockPriceQueueV2, FanoutExchange fanoutExchangeV2) {
        return BindingBuilder.bind(stockPriceQueueV2).to(fanoutExchangeV2);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public StatefulRetryOperationsInterceptor interceptor() {
        return RetryInterceptorBuilder.stateful()
                .maxAttempts(2)
                .backOffOptions(5000, 2.0, 10000) // initialInterval, multiplier, maxInterval
                .build();
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
