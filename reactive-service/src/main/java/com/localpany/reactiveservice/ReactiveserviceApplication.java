package com.localpany.reactiveservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class ReactiveserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveserviceApplication.class, args);
	}

}
