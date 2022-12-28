package com.example.nosqlservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("brands")
@Data
@ToString
public class Brand {
    @Id
    private String id;
    private String name;
    private String originCountry;
    private Integer fundYear;
}
