package com.example.nosqlservice.controller;

import com.example.nosqlservice.model.Brand;
import com.example.nosqlservice.repository.BrandRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/brand")
@Slf4j
public class CrudBrandController {
    @Autowired
    private BrandRepository brandRepository;

    @GetMapping("/")
    public ResponseEntity<Collection<Brand>> listAll(){
        var result = brandRepository.findAll();
        if(result==null || result.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.EMPTY_LIST);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Brand> getOne(@PathVariable String id){
        var result = brandRepository.findById(id);
        if(result.isPresent())
            return ResponseEntity.ok(result.get());
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/filter")
    public ResponseEntity<Collection<Brand>> getManyByCountry(@RequestParam(value = "country") String country){
        var result = brandRepository.findAllByCountry(country);
        if(result!=null && !result.isEmpty())
            return ResponseEntity.ok(result);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.EMPTY_LIST);
    }

    @PostMapping("/")
    public ResponseEntity<?> addOne(@RequestBody Brand brand){
        try {
            brand = brandRepository.insert(brand);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());

        }
    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(brand.getId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOne(@PathVariable String id){
        try{
            brandRepository.deleteById(id);
        }catch(Exception e){
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
        return  ResponseEntity.ok("");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOne(@PathVariable String id, @RequestBody Brand brand){
        try{
            if(!brandRepository.findById(id).isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("");
            }
            brand.setId(id);
            brandRepository.save(brand);
        }catch(Exception e){
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
        return  ResponseEntity.ok("");
    }
}
