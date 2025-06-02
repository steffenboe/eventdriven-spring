package com.exxeta.codebuzz.stockpricemonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
// @EnableScheduling
public class StockpriceMonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockpriceMonitorApplication.class, args);
	}



}
