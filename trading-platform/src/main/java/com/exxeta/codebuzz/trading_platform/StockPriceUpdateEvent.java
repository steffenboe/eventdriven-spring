package com.exxeta.codebuzz.trading_platform;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


// TODO #1: POJO for the stock price update event
/**
 * Event representing a stock price update.
 * This class is used to receive stock price updates via RabbitMQ.
 */
record StockPriceUpdateEvent(String version, String symbol, double priceInCents, Boolean flagged) {
    
}
