package com.example.apiconsumer.service.impl;

import com.example.apiconsumer.configuration.MappingAPIProperties;
import com.example.apiconsumer.model.Task;
import com.example.apiconsumer.model.enums.TaskType;
import com.example.apiconsumer.service.BoredService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.text.MessageFormat;

@NoArgsConstructor
@AllArgsConstructor
public class BoredServiceV2Impl implements BoredService {
    @NonNull
    OkHttpClient client;
    @NonNull
    ObjectMapper objectMapper;
    @NonNull
    private MappingAPIProperties.ApiService apiService;
    @Override
    public Task getRandomTask() {
        Request request = new Request
                .Builder()
                .get()
                .url(apiService.getHost()+ apiService.getServicesMap().get("random"))
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        try(Response response = client.newCall(request).execute()){
            return fromResponseBodyJSON(response);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Task getRandomTaskByType(TaskType type) {
        String url = MessageFormat.format(apiService.getServicesMap().get("type"), type.name());

        Request request = new Request
                .Builder()
                .get()
                .url(apiService.getHost()+ url)
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        try(Response response = client.newCall(request).execute()){
            return fromResponseBodyJSON(response);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private Task fromResponseBodyJSON(Response response){
        Task task;
        try{
            task = objectMapper.readValue(response.body().string(), Task.class);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return task;
    }
}
