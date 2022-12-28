package com.localpany.reactiveservice.service.impl;

import com.localpany.reactiveservice.exception.NotVerbFoundException;
import com.localpany.reactiveservice.model.WordVerb;
import com.localpany.reactiveservice.repository.WordVerbRepository;
import com.localpany.reactiveservice.service.WordVerbService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@Service
public class WordVerbServiceImpl implements WordVerbService {

    @Autowired
    @Qualifier("jdbcScheduler")
    private Scheduler jdbcScheduler;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private WordVerbRepository repository;

    public WordVerbServiceImpl() {
    }

    @Override
    public Flux<Page<WordVerb>> getAll(Pageable pageable) {
        return Flux.defer(() -> Flux.just(repository.findAll(pageable))
                .subscribeOn(jdbcScheduler));
    }

    @Override
    public Mono<WordVerb> post(WordVerb wordVerb) {
        return Mono.fromCallable(() -> transactionTemplate.execute(status -> {
            var verb = repository.save(wordVerb);
            return verb;
        })).subscribeOn(jdbcScheduler);
    }

    @Override
    public Mono<WordVerb> getById(Long id) {
        return Mono.defer(() -> {
                    var result = repository.findById(id);
                    if (result.isPresent())
                        return Mono.just(result.get());
                    return Mono.error(() -> new NotVerbFoundException(id));
                }).subscribeOn(jdbcScheduler)
                .onErrorResume(throwable -> Mono.error(new NotVerbFoundException(id)));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Mono<WordVerb> update(WordVerb wordVerb) {
        return Mono.fromCallable(() -> transactionTemplate.execute(status -> {
            var verb = repository.save(wordVerb);
            return verb;
        })).subscribeOn(jdbcScheduler);
    }
}
