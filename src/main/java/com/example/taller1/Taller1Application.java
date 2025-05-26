package com.example.taller1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.taller1.infraestructure.repositorys")
@EntityScan("com.example.taller1.domain")
public class Taller1Application {

	public static void main(String[] args) {
		SpringApplication.run(Taller1Application.class, args);
	}

}
