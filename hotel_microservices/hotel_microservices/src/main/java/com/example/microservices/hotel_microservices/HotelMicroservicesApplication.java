package com.example.microservices.hotel_microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan

public class HotelMicroservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelMicroservicesApplication.class, args);
	}

}
