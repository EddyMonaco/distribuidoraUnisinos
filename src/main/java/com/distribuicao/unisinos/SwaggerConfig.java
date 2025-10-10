package com.distribuicao.unisinos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.MessageSource;
import java.util.Locale;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    @Autowired
    private MessageSource messageSource;
     
    @Value("${app.version}")
    private String version;
     
    @Bean
    public OpenAPI getOpenAPI() {
        return new OpenAPI().info(new Info()
                .title(messageSource.getMessage("app.title", null, Locale.getDefault()))
                .version(version)
                .description(messageSource.getMessage("app.description", null, Locale.getDefault())));
    }

}