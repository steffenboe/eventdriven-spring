package com.exxeta.codebuzz.trading_platform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component
class StockUpdateEventListener {

    // private static final Logger LOG = LoggerFactory.getLogger(StockUpdateEventListener.class);

    // @RabbitListener(queues = "tradingPlattformStockPriceUpdate")
    // public void handleStockPriceUpdate(StockPriceUpdateEvent message) {
    // // Process the stock price update message
    // LOG.info("Received stock price update: {}", message);
    // }

    // @RabbitListener(queues = "tradingPlattformStockPriceUpdateV2")
    // public void handleStockPriceUpdate(StockPriceUpdateEventV2 message) {
    // LOG.info("Received stock price update v2: {}", message);
    // }

    // @RabbitListener(queues = "tradingPlattformStockPriceUpdateV2")
    // @Retryable(maxAttempts = 3, backoff = @org.springframework.retry.annotation.Backoff(delay = 2000))
    // public void handleStockPriceUpdateWithRetry(StockPriceUpdateEventV2 message) {
    //     LOG.info("Received stock price update v2: {}", message);
    //     // Simulate processing logic that might fail
    //     // For demonstration, we throw an exception to trigger the retry mechanism
    //     throw new RuntimeException("Simulated error in StockUpdateEventListener");
    // }

    // @Recover
    // public void recover(RuntimeException e, StockPriceUpdateEventV2 message) {
    //     LOG.error("Failed to process stock price update after retries: {}", message, e);
    //     // default: ack
    //     // with the following, the message will not be requeued:
    //     throw new AmqpRejectAndDontRequeueException("Failed to process stock price update", e);
    // }

}
