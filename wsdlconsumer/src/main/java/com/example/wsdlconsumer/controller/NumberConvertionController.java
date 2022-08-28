package com.example.wsdlconsumer.controller;

import com.example.wsdlconsumer.service.NumberConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.Map;

@RestController
@RequestMapping("/number")
public class NumberConvertionController {
    @Autowired
    private NumberConversionService service;

    @GetMapping("/convert/{number}")
    public ResponseEntity<Map<String, String>> convertToLetters(@PathVariable("number") BigInteger number){
        return ResponseEntity.ok(service.convertToLetters(number));
    }

    @GetMapping("/status")
    public ResponseEntity<String> status(){
        return ResponseEntity.ok("It´s up");
    }


}
