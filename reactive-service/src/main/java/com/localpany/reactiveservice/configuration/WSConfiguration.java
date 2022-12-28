package com.localpany.reactiveservice.configuration;

import com.localpany.reactiveservice.properties.WsdlProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WSConfiguration {
    @Bean
    @ConfigurationProperties(prefix = "app.wsdl")
    public WsdlProperties wsdlProperties(){
        return WsdlProperties.builder().build();
    }
}
