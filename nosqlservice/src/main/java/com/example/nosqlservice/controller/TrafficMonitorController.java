package com.example.nosqlservice.controller;

import com.example.nosqlservice.model.HttpRequestRegister;
import com.example.nosqlservice.repository.HttpRequestRegisterRepository;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/register")
public class TrafficMonitorController {

    @Autowired
    private HttpRequestRegisterRepository httpRequestRegisterRepository;

    @GetMapping("/apply")
    public ResponseEntity<?> registerGet(HttpServletRequest request,
                                         @RequestHeader Map<String, Object> headers){
        var response = createEntity(headers,request, null);
        httpRequestRegisterRepository.insert(response);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/apply")
    public ResponseEntity<?> registerPost(HttpServletRequest request,
                                          @RequestHeader Map<String, Object> headers,
                                          @RequestBody Object body){
        var response = createEntity(headers,request, body);
        httpRequestRegisterRepository.insert(response);
        return ResponseEntity.ok(response);
    }

    private String createIndex(){
        return UUID.randomUUID().toString();
    }

    private HttpRequestRegister createEntity(Map<String, Object> headers, HttpServletRequest request, Object body){
        return HttpRequestRegister.builder()
                .id(createIndex())
                .logDateTime(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
                .header(headers)
                .method(request.getMethod())
                .remoteAddress(request.getRemoteAddr())
                .path(request.getServletPath())
                .body(body==null?null:body.toString())
                .build();
    }

}
