package com.exxeta.codebuzz.stockpricemonitor;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

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
    private final RabbitTemplate rabbitTemplate;
    private final String url;

    StockPriceMonitor(RestTemplate restTemplate, RabbitTemplate amqpTemplate) {
        this.restTemplate = restTemplate;
        this.rabbitTemplate = amqpTemplate;
        try {
            url = getUrl();
            LOG.info("Fetching stock price from {}", url);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    @Scheduled(fixedRate = 5000)
    void monitorStockPrice() throws UnknownHostException, JsonProcessingException, NumberFormatException {
        String stockPrice = restTemplate.getForObject(url, String.class);
        LOG.info("Stock price received: " + stockPrice);

        // StockPriceUpdateEvent event = new StockPriceUpdateEvent("A0DJ5J",
        // Double.parseDouble(stockPrice), "some name");
        // rabbitTemplate.convertAndSend("stockPriceUpdate", "",
        // event);
        // LOG.info("Event published: {}", event);

        // StockPriceUpdateEventV2 eventV2 = new StockPriceUpdateEventV2(new
        // Symbol(UUID.randomUUID().toString(), "A0DJ5J"),
        // Double.parseDouble(stockPrice), "Some stock");
        // rabbitTemplate.convertAndSend("stockPriceUpdateV2", "",
        // eventV2);
        // LOG.info("Event V2 published: {}", eventV2);

        StockPriceUpdateEventV2 eventV2 = new StockPriceUpdateEventV2(
                new Symbol(UUID.randomUUID().toString(), "A0DJ5J"),
                Double.parseDouble(stockPrice), "Some stock");
        CorrelationData correlationData = new CorrelationData(eventV2.toString());

        rabbitTemplate.convertAndSend(
                "stockPriceUpdateV2",
                "",
                eventV2, correlationData);
    }

    private String getUrl() throws UnknownHostException {
        String hostname = "unknown";
        hostname = InetAddress.getLocalHost().getHostName();

        String url = "http://" + hostname + ":8080/api/stock/A0DJ5J";
        LOG.debug(url);
        return url;
    }
}
