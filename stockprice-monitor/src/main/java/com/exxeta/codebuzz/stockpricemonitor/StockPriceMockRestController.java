package com.exxeta.codebuzz.stockpricemonitor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Mock REST controller for stock prices.
 * This is a placeholder for the actual implementation.
 */
@RestController
public class StockPriceMockRestController {

    // TODO #1: Provide a Mock REST endpoint to simulate stock price retrieval
    @GetMapping("/api/stock/{symbol}")
    public ResponseEntity<StockPriceUpdateEvent> getStockPrice(@PathVariable String symbol) {
        StockPriceUpdateEvent stockPrice = new StockPriceUpdateEvent(symbol, Math.random() * 1000);
        return ResponseEntity.ok(stockPrice);
    }
    
}
