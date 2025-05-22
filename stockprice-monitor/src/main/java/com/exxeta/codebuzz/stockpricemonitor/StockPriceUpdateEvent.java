package com.exxeta.codebuzz.stockpricemonitor;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Event representing a stock price update.
 * This class is used to send stock price updates via RabbitMQ.
 */
record StockPriceUpdateEvent(@JsonProperty String symbol, @JsonProperty double priceInCents) {

}
