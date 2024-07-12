package com.cnbv.consultas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

	
	@Bean
	public OpenAPI customOpenAPI() {

		return new OpenAPI()
				.addServersItem(new Server().url("https://ofg-cp-solicitud-consultas-dev.cnbv.gob.mx:443"))
//				.addServersItem(new Server().url("https://ofg-cp-solicitud-consultas-qa.cnbv.gob.mx:443"))

				.addServersItem(new Server().url("http://localhost:8080"))

				.info(new Info()

						.title("Consultas")

						.version("1.0")

						.description("API Rest para Consulta Interna"));
	}
	
}
