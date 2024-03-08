package com.OllamaAi.OllamaAi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class OllamaAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OllamaAiApplication.class, args);
	}

}
