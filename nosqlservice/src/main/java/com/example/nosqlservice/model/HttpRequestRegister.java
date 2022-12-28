package com.example.nosqlservice.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;

@Document("TrafficGateway")
@Data
@ToString
@Builder
public class HttpRequestRegister {
    @Id
    private String id;
    private String logDateTime;
    private String method;
    private String path;
    private String remoteAddress;
    private Map<String, Object> header;
    private String body;
}
