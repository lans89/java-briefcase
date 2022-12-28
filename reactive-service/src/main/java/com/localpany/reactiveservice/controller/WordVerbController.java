package com.localpany.reactiveservice.controller;

import com.localpany.reactiveservice.exception.NotVerbFoundException;
import com.localpany.reactiveservice.model.WordVerb;
import com.localpany.reactiveservice.service.WordVerbService;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("verbs")
public class WordVerbController {
    @Autowired
    private WordVerbService service;

    @GetMapping("/hello")
    public Mono<String> greet(@RequestParam("name") String name) {
        if(name == null || name.trim().isEmpty()) return Mono.just("Just hello dear unknown");
        switch (name){
            case "1": return Mono.error(new RuntimeException("1"));
            case "2": return Mono.error(new NoSuchElementException("2"));
            case "3": return Mono.error(new NotVerbFoundException(3L));
            case "4": return Mono.error(new SQLGrammarException("", new SQLException()));
        }
        return Mono.just(String.format("Hello %s",name));
    }

    @GetMapping("/{id}")
    private Mono<WordVerb> getVerbById(@PathVariable Long id){
        return service.getById(id);
    }

    @GetMapping("/")
    private Flux<Page<WordVerb>> getAllVerbs(
            @RequestParam(value="size", required = false) Integer size,
            @RequestParam(value="page", required = false) Integer page,
            @RequestParam(value = "sort", required = false) String sort){
        if(size==null) size = 50;
        if(page==null) page = 0;
        PageRequest pageRequest = null;
        if(null==sort || sort.trim().isEmpty())
            pageRequest = PageRequest.of(page,size);
        else
            pageRequest = PageRequest.of(page, size, Sort.by(sort));
        return service.getAll(pageRequest);
    }

    @PostMapping("/")
    private Mono<WordVerb> addVerb(@RequestBody @Valid WordVerb wordVerb){
        return service.post(wordVerb);
    }

    @DeleteMapping("/{id}")
    private void deleteVerb(@PathVariable Long id){
        service.deleteById(id);
    }

    @PutMapping("/")
    private Mono<WordVerb> updateVerb(@RequestBody @Valid WordVerb verb){
        return service.update(verb);
    }
}
