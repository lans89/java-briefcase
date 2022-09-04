package com.home.hn.apiconsumer.repository;

import com.home.hn.apiconsumer.model.repository.ApiModel;

import java.util.Optional;

public interface ParamApiRepository {
    Optional<ApiModel> getApiModel(String name);
}
