package io.github.yanglong.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"io.github.yanglong.demo"})
public class WebsocketApplication{

	public static void main(String[] args) {
		SpringApplication.run(WebsocketApplication.class, args);
	}
}
