package com.exxeta.codebuzz.stockpricemonitor;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Instant;
import java.util.Date;

import org.slf4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

@Component
class StockPriceMonitor {

    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(StockPriceMonitor.class);

    private final RestTemplate restTemplate;
    private final RabbitTemplate amqpTemplate;
    private final String url;

    // TODO #2: Provide constructor
    StockPriceMonitor(RestTemplate restTemplate, RabbitTemplate amqpTemplate) {
        this.restTemplate = restTemplate;
        this.amqpTemplate = amqpTemplate;
        try {
            url = getUrl();
            LOG.info("Fetching stock price from {}", url);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    // TODO #3: Provide a scheduled task to fetch stock price every 5 seconds and
    // publish to queue
    @Scheduled(fixedRate = 5000)
    void monitorStockPrice() throws UnknownHostException, JsonProcessingException, NumberFormatException {
        String stockPrice = restTemplate.getForObject(url, String.class);
        // TODO 7.1: Use JSON serialization
        // amqpTemplate.convertAndSend("stockPriceUpdate",
        // new StockPriceUpdateEvent("1.0", "A0DJ5J",
        // Double.parseDouble(stockPrice), "Some name"));
        // TODO publisher confirms
        CorrelationData correlationData = new CorrelationData(new StockPriceUpdateEventV2(
                        new Version("1", Date.from(Instant.parse("2007-12-03T10:15:30.00Z"))),
                        "A0DJ5J",
                        Double.parseDouble(stockPrice),
                        "Some name").toString());
        
        amqpTemplate.convertAndSend(
                "ABC_NOT_EXISTING",
                "",
                new StockPriceUpdateEventV2(
                        new Version("1", Date.from(Instant.parse("2007-12-03T10:15:30.00Z"))),
                        "A0DJ5J",
                        Double.parseDouble(stockPrice),
                        "Some name"), correlationData);
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
