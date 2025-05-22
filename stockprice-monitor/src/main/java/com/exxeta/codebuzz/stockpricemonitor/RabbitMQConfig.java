package com.exxeta.codebuzz.stockpricemonitor;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

// TODO #6 Define a RabbitMQ configuration
@Configuration
public class RabbitMQConfig {
    
    @Bean
    public Queue stockPriceQueue() {
        return new Queue("stockPriceUpdate", false);
    }

    @Bean
	public FanoutExchange fanout() {
		return new FanoutExchange("stockPriceUpdate");
	}

    @Bean
    public Binding stockPriceBinding(Queue stockPriceQueue, FanoutExchange stockPriceExchange) {
        return BindingBuilder.bind(stockPriceQueue).to(stockPriceExchange);
    }

    // TODO #7 define message converting
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        ObjectMapper mapper = new ObjectMapper();
        return new Jackson2JsonMessageConverter(mapper);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
