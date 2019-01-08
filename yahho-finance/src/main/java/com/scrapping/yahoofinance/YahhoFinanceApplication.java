package com.scrapping.yahoofinance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.scrapping.yahoofinance.*"})
public class YahhoFinanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(YahhoFinanceApplication.class, args);
	}

}

