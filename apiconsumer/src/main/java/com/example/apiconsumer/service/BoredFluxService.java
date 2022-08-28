package com.example.apiconsumer.service;

import com.example.apiconsumer.configuration.MappingAPIProperties;
import com.example.apiconsumer.model.Task;
import com.example.apiconsumer.model.enums.TaskType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@NoArgsConstructor
@AllArgsConstructor
public class BoredFluxService {

    @NonNull
    private MappingAPIProperties.ApiService apiService;

    public Mono<Task> getRandomTaskV2() {
        WebClient client = WebClient.create(apiService.getHost());
        return client.get().uri(apiService.getServicesMap().get("random"))
                .retrieve().bodyToMono(Task.class);
    }

    public Mono<Task> getRandomTaskByTypeV2(TaskType type) {
        String urlToUse = apiService.getServicesMap().get("type");
        WebClient client = WebClient.create(apiService.getHost());
        return client.get().uri(urlToUse, type.name())
                .retrieve().bodyToMono(Task.class);
    }
}
