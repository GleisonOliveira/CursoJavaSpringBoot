package com.gleisonoliveira.personapi.Converter;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public class YamlHttpMessageConverter extends AbstractJackson2HttpMessageConverter {

    public YamlHttpMessageConverter() {
        super(new YAMLMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL),
                MediaType.parseMediaType(com.gleisonoliveira.personapi.Util.MediaType.APPLICATION_YAML));
    }
}
