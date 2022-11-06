package com.home.service.controller;

import com.home.service.model.GeneralResponse;
import com.home.service.model.Header;
import com.home.service.model.WordMeaning;
import com.home.service.repository.JPAWordMeaning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/words")
public class WordMeaningController {

    @Autowired
    private JPAWordMeaning repository;

    @PostMapping("/")
    public GeneralResponse save(@RequestBody WordMeaning word){
        var item = repository.save(word);
        return getResponseOK(item.getId());
    }

    @GetMapping("/{id}")
    public GeneralResponse getOne(@PathVariable Long id){
        var item = repository.findById(id);
        if(item.isPresent()) return getResponseOK(item.get());
        return getResponseError(2, "Item not found");
    }

    @GetMapping("/")
    public GeneralResponse getAll(){
        var item = repository.findAll();
        if(item.size()>0) return getResponseOK(item);
        return getResponseError(2, "List of Items not found");
    }

    private GeneralResponse getResponseOK(Object response){
        return GeneralResponse.builder()
                .header(Header.builder()
                        .description("Successful")
                        .code(0).build())
                .response(response)
                .build();
    }

    private GeneralResponse getResponseError(Integer code, String message){
        return GeneralResponse.builder()
                .header(Header.builder()
                        .description(message)
                        .code(code).build())
                .build();
    }

}
