package com.gleisonoliveira.personapi.Configuration;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gleisonoliveira.personapi.Converter.YamlHttpMessageConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new YamlHttpMessageConverter());
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorParameter(false)
                .ignoreAcceptHeader(false)
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("x-yaml", MediaType.valueOf(com.gleisonoliveira.personapi.Util.MediaType.APPLICATION_YAML))
                .mediaType("xml", MediaType.APPLICATION_XML);
    }

}
