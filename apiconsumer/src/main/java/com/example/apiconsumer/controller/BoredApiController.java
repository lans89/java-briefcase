package com.example.apiconsumer.controller;

import com.example.apiconsumer.model.Task;
import com.example.apiconsumer.model.enums.TaskType;
import com.example.apiconsumer.service.BoredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/bored/v1")
public class BoredApiController {
    @Autowired(required = true)
    @Qualifier("webRestTemplate")
    private BoredService boredservice;

    @GetMapping("/request")
    public Task getRandomTask(){
        return boredservice.getRandomTask();
    }

    @GetMapping("/request/filter")
    public Task getRandomTaskByType(@RequestParam("type") TaskType type){
        return boredservice.getRandomTaskByType(type);
    }

}
