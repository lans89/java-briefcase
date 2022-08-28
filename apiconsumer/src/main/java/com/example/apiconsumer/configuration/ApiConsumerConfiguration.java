package com.example.apiconsumer.configuration;

import com.example.apiconsumer.service.BoredService;
import com.example.apiconsumer.service.BoredFluxService;
import com.example.apiconsumer.service.impl.BoredServiceImpl;
import com.example.apiconsumer.service.impl.BoredServiceV2Impl;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApiConsumerConfiguration {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public OkHttpClient client(){ return new OkHttpClient(); }

    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    @Bean(name = "webRestTemplate")
    public BoredService boredService(RestTemplate restTemplate, MappingAPIProperties mappingAPIProperties){
        return new BoredServiceImpl(restTemplate, mappingAPIProperties.getMappingService().get("bored"));
    }

    @Bean(name = "webHttpOK3")
    public BoredService boredService(OkHttpClient client, ObjectMapper objectMapper, MappingAPIProperties mappingAPIProperties){
        return new BoredServiceV2Impl(client, objectMapper, mappingAPIProperties.getMappingService().get("bored"));
    }

    @Bean(name = "webReactiveFlux")
    public BoredFluxService boredFluxService(MappingAPIProperties mappingAPIProperties){
        return new BoredFluxService( mappingAPIProperties.getMappingService().get("bored") );
    }
}
