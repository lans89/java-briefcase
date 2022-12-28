package com.localpany.reactiveservice.controller;

import com.localpany.reactiveservice.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@Slf4j
@RequestMapping("login/")
public class LoginController{

    @Autowired
    private LoginService loginService;

    @PostMapping
    public Mono<Boolean> validate(@RequestBody Map<String, String> body){
        log.info("request-body >> {}", body.toString());
        String user = body.getOrDefault("USER","");
        String password = body.getOrDefault("PWRD", "");
        return loginService.verify(user,password);
    }
}
