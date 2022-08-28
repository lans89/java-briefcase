package com.example.wsdlconsumer.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "soap-service.country")
public class CountryProperties {
    private String packages;
    private String endpoint;
}
