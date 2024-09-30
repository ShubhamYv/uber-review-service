package com.reviewservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EntityScan(basePackages = "com.entityservice.models")
public class UberReviewServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UberReviewServiceApplication.class, args);
	}

}
