package com.weather.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.weather.application.controller", "com.weather.application.services" })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}

}
