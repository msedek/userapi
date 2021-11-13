package com.globallogic.userapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserApiApplication {

	protected static final Logger logger = LoggerFactory.getLogger(UserApiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(UserApiApplication.class, args);
		logger.info("Test app started perfectly!");
	}
}
