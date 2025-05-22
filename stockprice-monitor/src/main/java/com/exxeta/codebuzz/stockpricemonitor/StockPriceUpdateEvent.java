package com.exxeta.codebuzz.stockpricemonitor;

/**
 * Event representing a stock price update.
 * This class is used to send stock price updates via RabbitMQ.
 */
record StockPriceUpdateEvent(String version, String symbol, double priceInCents, String name) {

}
