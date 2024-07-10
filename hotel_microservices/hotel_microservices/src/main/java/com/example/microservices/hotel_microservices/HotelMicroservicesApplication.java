package com.example.microservices.hotel_microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.ControllerAdvice;

@SpringBootApplication

@EnableJpaRepositories(basePackages = "repo")
@EntityScan(basePackages = "Entity")
@ControllerAdvice
public class HotelMicroservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelMicroservicesApplication.class, args);
	}

}
