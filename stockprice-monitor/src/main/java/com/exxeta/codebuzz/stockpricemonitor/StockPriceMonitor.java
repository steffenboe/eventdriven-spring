package com.exxeta.codebuzz.stockpricemonitor;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
class StockPriceMonitor {
    
    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(StockPriceMonitor.class);

    private final RestTemplate restTemplate;
    private final AmqpTemplate amqpTemplate;
    private final String url;

    // TODO #2: Provide constructor
    StockPriceMonitor(RestTemplate restTemplate, AmqpTemplate amqpTemplate) {
        this.restTemplate = restTemplate;
        this.amqpTemplate = amqpTemplate;
        try {
            url = getUrl();
            LOG.info("Fetching stock price from {}", url);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    // TODO #3: Provide a scheduled task to fetch stock price every 5 seconds and publish to queue
    @Scheduled(fixedRate = 5000)
    void monitorStockPrice() throws UnknownHostException {
        String stockPrice = restTemplate.getForObject(url, String.class);

        // TODO #5: Send the stock price to the RabbitMQ queue
        // by default, uses java serialization
        //amqpTemplate.convertAndSend("stockPriceUpdate", stockPrice);
        // TODO 7.1: Use JSON serialization
        amqpTemplate.convertAndSend("stockPriceUpdate", new StockPriceUpdateEvent("1.0", "A0DJ5J", Double.parseDouble(stockPrice), "Some Stock"));
        LOG.info("Stock price sent to queue: " + stockPrice);
    }

    private String getUrl() throws UnknownHostException {
        String hostname = "unknown";
        hostname = InetAddress.getLocalHost().getHostName();

        String url = "http://" + hostname + ":8080/api/stock/A0DJ5J";
        LOG.debug(url);
        return url;
    }
}
