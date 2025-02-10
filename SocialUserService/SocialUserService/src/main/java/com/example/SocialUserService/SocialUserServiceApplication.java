package com.example.SocialUserService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SocialUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialUserServiceApplication.class, args);
	}

}
