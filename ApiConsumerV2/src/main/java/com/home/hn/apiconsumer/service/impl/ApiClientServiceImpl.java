package com.home.hn.apiconsumer.service.impl;

import com.home.hn.apiconsumer.exception.ParameterValueNotFoundException;
import com.home.hn.apiconsumer.model.UseApiRequest;
import com.home.hn.apiconsumer.model.repository.ApiDetailModel;
import com.home.hn.apiconsumer.model.repository.ApiModel;
import com.home.hn.apiconsumer.service.ApiClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ApiClientServiceImpl implements ApiClientService {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public <T>Optional<T> consume(UseApiRequest request, ApiModel apiModel, Class<T> clazzResponse) {
        StringBuilder pathUrl = new StringBuilder(String.format("%s:%s%s", apiModel.getHost(), apiModel.getPort(), apiModel.getPath()));
        String method = apiModel.getMethod();
        StringBuilder pathQuery = new StringBuilder("?");
        T result = null;

        //Add headers
        HttpHeaders headers = createHeaders(request, apiModel.getHeaders());
        log.info("Created Header: {}", headers.toSingleValueMap().toString());

        //Create EntityRequest
        HttpEntity<?> httpEntity = new HttpEntity<>(request.getContent(), headers);

        //Add queryParam
        pathQuery = createQueryParan(request, apiModel.getQueryParams());

        log.info("API Fields -> METHOD: {} HOSTAPI:{}{}",method,pathUrl,pathQuery);
        pathUrl.append(pathQuery);

        //Consume the API
        result = consumeApi(method, pathUrl.toString(), httpEntity, clazzResponse);

        //Return de body result
        return Optional.ofNullable(result);
    }

    private <T> T consumeApi(String method, String pathUrl, HttpEntity<?> httpEntity, Class<T> clazzResponse) {
        return switch (method){
            case "POST" -> restTemplate.exchange(pathUrl.toString(), HttpMethod.POST, httpEntity, clazzResponse).getBody();
            case "PUT" -> restTemplate.exchange(pathUrl.toString(), HttpMethod.PUT, httpEntity, clazzResponse).getBody();
            case "DELETE" -> restTemplate.exchange(pathUrl.toString(), HttpMethod.DELETE, httpEntity, clazzResponse).getBody();
            default -> restTemplate.exchange(pathUrl.toString(), HttpMethod.GET, httpEntity, clazzResponse).getBody();
        };
    }

    private StringBuilder createQueryParan(UseApiRequest request, List<ApiDetailModel> queryParams) {
        StringBuilder sbQueryParams = new StringBuilder("");
        Map<String, String> queryMapParameters= new LinkedHashMap<>();
        if (!queryParams.isEmpty()) {

            queryParams.stream()
                    .forEach(q -> {
                        boolean hasValueFromMap = request.getQueryParams().containsKey(q.getName());
                        boolean hasDefaultValue = !q.getDefaultValue().isBlank();

                        if(hasValueFromMap){
                            queryMapParameters.put(q.getName(), request.getQueryParams().get(q.getName()));
                        }else if(hasDefaultValue){
                            queryMapParameters.put(q.getName(), q.getDefaultValue());
                        }
                    });

            if(queryMapParameters.size()>0){
                sbQueryParams = new StringBuilder("?");
                Iterator<Map.Entry<String, String>> iterator = queryMapParameters.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> entry = iterator.next();
                    sbQueryParams.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
                }
            }

            if (sbQueryParams.toString().endsWith("&")) {
                sbQueryParams.delete(sbQueryParams.lastIndexOf("&"), sbQueryParams.length());
            }
        }
        return sbQueryParams;
    }

    private HttpHeaders createHeaders(UseApiRequest request, List<ApiDetailModel> apiModelHeaders) {
        HttpHeaders headers = new HttpHeaders();
        if (!apiModelHeaders.isEmpty()) {
            apiModelHeaders.stream().forEach(h -> {
                boolean hasValueFromMap = request.getHeaders().containsKey(h.getName());
                boolean hasDefaultValue = !h.getDefaultValue().isBlank();

                if (hasValueFromMap){
                    headers.add(h.getName(),request.getHeaders().get(h.getName()));
                }else if (hasDefaultValue) {
                    headers.add(h.getName(), h.getDefaultValue());
                }else{
                    throw new ParameterValueNotFoundException(HttpStatus.BAD_REQUEST, h.getName());
                }

            });
        }
        return headers;
    }
}
