package com.exxeta.codebuzz.stockpricemonitor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
class StockPriceMockRestController {

    @GetMapping("/api/stock/{symbol}")
    ResponseEntity<Double> getStockPrice(@PathVariable String symbol) {
        return ResponseEntity.ok(Math.random() * 1000);
    }
    
}
