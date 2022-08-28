package com.example.apiconsumer.service;

import com.example.apiconsumer.model.Task;
import com.example.apiconsumer.model.enums.TaskType;

public interface BoredService {
    Task getRandomTask();

    Task getRandomTaskByType(TaskType type);
}
