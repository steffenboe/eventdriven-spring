package com.exxeta.codebuzz.trading_platform;

import javax.management.RuntimeErrorException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
class StockUpdateEventListener {

    // private static final Logger LOG = LoggerFactory.getLogger(StockUpdateEventListener.class);

    // @RabbitListener(queues = "tradingPlattformStockPriceUpdate")
    // public void handleStockPriceUpdate(StockPriceUpdateEvent message) {
    //     // Process the stock price update message
    //     LOG.info("Received stock price update: {}", message);
    // }
    
    // @RabbitListener(queues = "tradingPlattformStockPriceUpdateV2")
    // public void handleStockPriceUpdate(StockPriceUpdateEventV2 message) {
    //     LOG.info("Received stock price update: {}", message);
    // }

}
