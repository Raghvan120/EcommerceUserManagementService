package com.ecommerce.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;

@Configuration
public class UserSwaggerConfig {
	
	@Bean
	public OpenAPI userServiceOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("ECommerce-User-service").description("ECommerce-User-service API Application").version("0.0.1")
						.license(new License().name("Apache 2.0").url("http://springdoc.org")))
				.externalDocs(new ExternalDocumentation().description("ECommerce-User-service Documentation")
					//	.url("https://springshop.wiki.github.org/docs")
						)
				.components(new Components().addSecuritySchemes("bearer-key",
						new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
								.in(In.HEADER).name("Authorization")))
				.addSecurityItem(new SecurityRequirement().addList("bearer-key"));
	}

}
