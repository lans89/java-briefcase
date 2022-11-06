package com.home.service.configuration;

import com.home.service.interfaces.MemoryService;
import com.home.service.interfaces.impl.MemoryServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationApp {
    @Bean
    public MemoryService memoryService(){
        return new MemoryServiceImpl();
    }
}
