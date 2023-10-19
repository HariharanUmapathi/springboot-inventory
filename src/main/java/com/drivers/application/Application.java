package com.drivers.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		final Logger logger = LoggerFactory.getLogger(Application.class);
		logger.info("Application Started");
		SpringApplication.run(Application.class, args);

	}

}
