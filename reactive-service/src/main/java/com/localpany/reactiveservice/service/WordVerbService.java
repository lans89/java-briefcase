package com.localpany.reactiveservice.service;

import com.localpany.reactiveservice.model.WordVerb;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface WordVerbService{
    Flux<Page<WordVerb>> getAll(Pageable pageable);
    Mono<WordVerb> post(WordVerb wordVerb);
    Mono<WordVerb> getById(Long id);
    void deleteById(Long id);
    Mono<WordVerb> update(WordVerb wordVerb);
}
