package com.home.hn.apiconsumer.model;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class UseApiRequest {
    @NotBlank(message = "The name of api config is required")
    private String name;
    private Map<@NotBlank(message="Key to header can't be empty") String, @NotBlank(message="Value to header can't be empty") String> headers = new LinkedHashMap<>();
    private Map<@NotBlank(message="Key to query parameter can't be empty")String, @NotBlank(message="Value to query parameter can't be empty")String> queryParams = new LinkedHashMap<>();
    private Map<String, String> pathParams;
    private Map<String, String> formParams;
    private String content;
}
