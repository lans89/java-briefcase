package com.home.hn.apiconsumer.model.repository;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiDetailModel {
    private String name;
    private String defaultValue;
    private Boolean required;
}
