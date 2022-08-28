package com.example.apiconsumer.configuration;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(doNotUseGetters = false)
@Configuration
@ConfigurationProperties(prefix = "api-services")
public class MappingAPIProperties {
    private Map<String, ApiService> mappingService;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @ToString(doNotUseGetters = false)
    public static class ApiService{
        private String host;
        private Map<String, String> servicesMap;
    }
}
