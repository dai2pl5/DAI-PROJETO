package com.smart_start.jpa.smartstart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages="com.smart_start.jpa.smartstart.repository")
@SpringBootApplication
public class SmartStartApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartStartApplication.class, args);
	}

}
