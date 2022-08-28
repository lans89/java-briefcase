package com.example.apiconsumer.model;

import com.example.apiconsumer.model.enums.TaskType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private Long key;
    private String activity;
    private TaskType type;
    private Float price;
}
