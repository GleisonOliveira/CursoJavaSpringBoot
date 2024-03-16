package com.gleisonoliveira.personapi.Configuration;

import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.Links;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        SpringDocUtils.getConfig().addResponseTypeToIgnore(Links.class);
        
        var info = new Info()
                .title("API Rest")
                .version("v1")
                .description("API rest description");

        return new OpenAPI().info(info);
    }
}
