package com.localpany.reactiveservice.service;

import reactor.core.publisher.Mono;

public interface LoginService {
    public Mono<Boolean> verify(String user, String password);
}
