package com.mnasro.demo.configuration;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
@OpenAPIDefinition
public class SwaggerConfig {

	@Bean
	public OpenAPI springShopOpenAPI() {

		return new OpenAPI()
				.info(new Info().title("API").description("This Spring Boot application provides an API for for Accept deal details and persist them in the DB via RESTful endpoints and persists them in the database.").version("v0.0.1")
						.license(new License().name("Apache 2.0").url("http://springdoc.org")))
				.externalDocs(new ExternalDocumentation().description("SpringShop Wiki Documentation")
						.url("https://springshop.wiki.github.org/docs"));
	}

	@Bean
	public GroupedOpenApi demoApi() {
		return GroupedOpenApi.builder().group("API V1").pathsToMatch("/v1/**").build();
	}


}