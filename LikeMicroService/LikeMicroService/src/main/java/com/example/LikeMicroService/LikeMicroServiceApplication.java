package com.example.LikeMicroService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LikeMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LikeMicroServiceApplication.class, args);
	}

}
