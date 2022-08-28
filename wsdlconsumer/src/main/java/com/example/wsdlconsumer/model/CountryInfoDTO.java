package com.example.wsdlconsumer.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Builder
@ToString
public class CountryInfoDTO {
    private String isoCode;
    private String name;
    private String capitalCity;
    private String phoneCode;
    private String continentCode;
    private String currencyIsoCode;
    private String countryFlag;
    private List<CountryLanguage> languages;
}
