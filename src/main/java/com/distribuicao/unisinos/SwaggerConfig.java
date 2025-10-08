package com.distribuicao.unisinos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.distribuicao.unisinos.service.MessageService;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
	@Autowired
	private MessageService messageService;
	
	@Value("${app.version}")
	private String version;
	
	@Bean
	public OpenAPI getOpenAPI() {
		return new OpenAPI().info(new Info()
				.title(messageService.getMessage("app.title"))
				.version(version)
				.description(messageService.getMessage("app.description")));
	}

}
