package com.home.hn.apiconsumer.service.impl;

import com.home.hn.apiconsumer.exception.ConfigurationApiNotFoundException;
import com.home.hn.apiconsumer.model.UseApiRequest;
import com.home.hn.apiconsumer.model.repository.ApiModel;
import com.home.hn.apiconsumer.repository.ParamApiRepository;
import com.home.hn.apiconsumer.service.ApiClientService;
import com.home.hn.apiconsumer.service.ApiParamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ApiParamServiceImpl implements ApiParamService{

    @Autowired
    private ParamApiRepository dao;

    @Autowired
    private ApiClientService apiClientService;

    @Override
    public Optional<?> useApi(UseApiRequest request, Class<?> clazzResponse) {
        var apiDBConf = dao.getApiModel(request.getName())
                .orElseThrow(() ->
                        new ConfigurationApiNotFoundException(HttpStatus.NOT_FOUND, request.getName()));
        return apiClientService.consume(request, apiDBConf, clazzResponse);
    }
}
