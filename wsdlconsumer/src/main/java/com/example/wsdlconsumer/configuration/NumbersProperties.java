package com.example.wsdlconsumer.configuration;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(doNotUseGetters = false)
@Configuration
@ConfigurationProperties(prefix = "soap-service.number")
public class NumbersProperties {
    private String packages;
    private String endpoint;
}
