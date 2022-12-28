package com.localpany.reactiveservice.service;

import com.localpany.reactiveservice.model.InfoCountry;
import reactor.core.publisher.Mono;

public interface WSCountries {

    public Mono<InfoCountry> getFullInfoCountry(String codeCountry);

}
