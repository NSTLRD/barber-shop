package com.mentorly.barber_shop;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(info = @io.swagger.v3.oas.annotations.info.Info(
		title = "Bank Stark",
		version = "1.0",
		description = "Securely manage user registrations and authentications. Provides JSON Web Tokens (JWT) on registration and login, which are required for subsequent authenticated requests."
))
public class BarberShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(BarberShopApplication.class, args);
	}

	@Bean
	public GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder()
				.group("public-api")
				.pathsToMatch("/api/v1/users/**", "/api/v1/account/**", "/api/v1/transactions/**")
				.build();
	}
}
