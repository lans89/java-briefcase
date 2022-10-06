package com.home.hn.apiconsumer.service;

import com.home.hn.apiconsumer.model.UseApiRequest;
import com.home.hn.apiconsumer.model.repository.ApiModel;

import java.util.Optional;

public interface ApiClientService {
    <T> Optional<T> consume(UseApiRequest request, ApiModel apiModel, Class<T> clazzResponse);
}
