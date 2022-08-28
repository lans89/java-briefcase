package com.example.apiconsumer.controller;

import com.example.apiconsumer.model.Task;
import com.example.apiconsumer.model.enums.TaskType;
import com.example.apiconsumer.service.BoredFluxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/bored/v2")
public class BoredApiV2Controller {
    @Autowired
    private BoredFluxService boredFluxService;

    @GetMapping("/request")
    public Mono<Task> getRandomTask(){
        return boredFluxService.getRandomTaskV2();
    }

    @GetMapping("/request/filter")
    public Mono<Task> getRandomTaskByType(@RequestParam("type") TaskType type){
        return boredFluxService.getRandomTaskByTypeV2(type);
    }
}
