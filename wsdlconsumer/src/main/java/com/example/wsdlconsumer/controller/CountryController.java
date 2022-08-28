package com.example.wsdlconsumer.controller;

import com.example.country.client.gen.FullCountryInfoResponse;
import com.example.wsdlconsumer.model.CountryInfoDTO;
import com.example.wsdlconsumer.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/country")
public class CountryController {
    @Autowired
    private CountryService service;

    @GetMapping("/info/{code}")
    public ResponseEntity<CountryInfoDTO> getAllInfoCountry(@PathVariable("code")String code){
        CountryInfoDTO response = service.getAllInfoCountry(code.toUpperCase());
        Optional<String> validate = Optional.ofNullable(response.getIsoCode());
        if (validate.isPresent() && validate.get().trim().length()>0){
            return ResponseEntity.ok(response);
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/status")
    public ResponseEntity<String> status(){
        return ResponseEntity.ok("It´s up");
    }

}
