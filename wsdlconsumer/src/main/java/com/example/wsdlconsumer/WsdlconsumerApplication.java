package com.example.wsdlconsumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class WsdlconsumerApplication {

	public static void main(String[] args) {
		LOGGER.info("Staring the process...");
		SpringApplication.run(WsdlconsumerApplication.class, args);
		LOGGER.info("Listening...");
	}

}
