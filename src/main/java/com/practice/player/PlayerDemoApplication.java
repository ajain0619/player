package com.practice.player;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.practice.player")
public class PlayerDemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(PlayerDemoApplication.class, args);
	}

}
