package com.exxeta.codebuzz.stockpricemonitor;

import java.io.Serializable;

// TODO #1: POJO for the stock price update event
/**
 * Event representing a stock price update.
 * This class is used to receive stock price updates via RabbitMQ.
 */
public record StockPriceUpdateEvent(Version version, String symbol, double priceInCents, boolean isFlagged) implements Serializable {
    
    
}
// record StockPriceUpdateEvent(String version, String symbol, double priceInCents, Boolean flagged) {
    
// }
