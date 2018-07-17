package com.nixsolutions.micrometr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class MicrometrApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicrometrApplication.class, args);
	}
}
