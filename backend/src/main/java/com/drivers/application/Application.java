package com.drivers.application;

/* import java.util.Arrays; */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/* import org.springframework.context.ApplicationContext; */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		final Logger logger = LoggerFactory.getLogger(Application.class);
		logger.info("Application Started");
		/* ApplicationContext ctx = */ SpringApplication.run(Application.class, args);

		/*
		 * System.out.println("Let's inspect the beans provided by Spring Boot:");
		 * 
		 * String[] beanNames = ctx.getBeanDefinitionNames();
		 * Arrays.sort(beanNames);
		 * for (String beanName : beanNames) {
		 * System.out.println(beanName);
		 * }
		 */
	}

}
