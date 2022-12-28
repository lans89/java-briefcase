package com.localpany.reactiveservice.controller;

import com.localpany.reactiveservice.model.InfoCountry;
import com.localpany.reactiveservice.service.WSCountries;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("country/")
@Slf4j
public class WSCountryController {
    @Autowired
    private WSCountries wsCountries;

    @GetMapping("{code}")
    public Mono<InfoCountry> getCountry(@PathVariable("code") String codeCountry){
        return wsCountries.getFullInfoCountry(codeCountry);
    }

}
