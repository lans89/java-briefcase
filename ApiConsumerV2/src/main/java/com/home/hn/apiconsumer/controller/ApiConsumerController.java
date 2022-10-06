package com.home.hn.apiconsumer.controller;

import com.home.hn.apiconsumer.model.UseApiRequest;
import com.home.hn.apiconsumer.repository.ParamApiRepository;
import com.home.hn.apiconsumer.service.ApiParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/")
public class ApiConsumerController {

    @Autowired
    private ApiParamService service;

    @PostMapping("use/")
    ResponseEntity<Map<String, Object>> useApi() {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("time", LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @PostMapping("api/")
    ResponseEntity<Map<String, Object>> useApiV2(@Valid @RequestBody UseApiRequest request) {

        var x = service.useApi(request, Object.class);
        if(!x.isEmpty()){
            Map<String, Object> response = new LinkedHashMap<>();
            response.put("time", LocalDateTime.now());
            response.put("response", x.get());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.noContent().build();
    }
}
