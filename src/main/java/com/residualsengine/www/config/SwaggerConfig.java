package com.residualsengine.www.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI getOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Residuals Engine").description("Spring Boot for managing residuals"));
	}
}
