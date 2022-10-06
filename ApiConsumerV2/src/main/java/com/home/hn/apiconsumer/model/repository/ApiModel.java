package com.home.hn.apiconsumer.model.repository;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ApiModel {
    private Long id;
    private String host;
    private String name;
    private String path;
    private Integer port;
    private String method;

    private List<ApiDetailModel> headers;
    private List<ApiDetailModel> queryParams;
    private List<ApiDetailModel> pathParams;
    private List<ApiDetailModel> formParams;

}
