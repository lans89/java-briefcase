package com.home.hn.apiconsumer.service;

import com.home.hn.apiconsumer.model.UseApiRequest;

import java.util.Optional;

public interface ApiParamService{
    Optional<?> useApi(UseApiRequest request, Class<?> clazz);
}
