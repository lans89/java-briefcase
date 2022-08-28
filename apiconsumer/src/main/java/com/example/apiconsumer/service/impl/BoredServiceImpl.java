package com.example.apiconsumer.service.impl;

import com.example.apiconsumer.configuration.MappingAPIProperties;
import com.example.apiconsumer.model.Task;
import com.example.apiconsumer.model.enums.TaskType;
import com.example.apiconsumer.service.BoredService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;

//RestTemplate will be deprecated in future version, remember it
@NoArgsConstructor
@AllArgsConstructor
public class BoredServiceImpl implements BoredService {
    @NonNull
    private RestTemplate restTemplate;

    @NonNull
    private MappingAPIProperties.ApiService apiService;

    @Override
    public Task getRandomTask() {
        String urlToUse = apiService.getHost() + apiService.getServicesMap().get("random");
        return restTemplate
                .getForEntity(urlToUse, Task.class)
                .getBody();
    }

    @Override
    public Task getRandomTaskByType(TaskType type){
        String urlToUse = apiService.getHost() + apiService.getServicesMap().get("type");
        urlToUse = urlToUse.replace("{0}", type.name());
        return restTemplate
                .getForEntity(urlToUse, Task.class)
                .getBody();
    }
}
