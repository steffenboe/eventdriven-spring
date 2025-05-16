package com.exxeta.codebuzz.stockpricemonitor;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
class StockPriceMonitor {
    
    private final RestTemplate restTemplate;
    private final AmqpTemplate amqpTemplate;

    StockPriceMonitor(RestTemplate restTemplate, AmqpTemplate amqpTemplate) {
        this.restTemplate = restTemplate;
        this.amqpTemplate = amqpTemplate;
    }

    @Scheduled(fixedRate = 5000)
    void monitorStockPrice() {
        String url = "https://api.example.com/stockprice"; // Replace with actual API URL
        String stockPrice = restTemplate.getForObject(url, String.class);
        amqpTemplate.convertAndSend("stockPriceQueue", stockPrice);
        System.out.println("Stock price sent to queue: " + stockPrice);
    }
}
