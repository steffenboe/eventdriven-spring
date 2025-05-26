package com.exxeta.codebuzz.trading_platform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.RabbitListenerErrorHandler;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

@Component("customErrorHandler")
class CustomErrorHandler implements RabbitListenerErrorHandler {

    private static final Logger LOG = LoggerFactory.getLogger(CustomErrorHandler.class);

    @Override
    public Object handleError(Message amqpMessage, Channel channel,
            @Nullable org.springframework.messaging.Message<?> message, ListenerExecutionFailedException exception)
            throws Exception {
        LOG.error("ERROR DETECTED!", exception);
        return null;
    }
    
  
}
