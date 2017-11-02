package com.game.draw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class GameDrawApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameDrawApplication.class, args);
	}
}
