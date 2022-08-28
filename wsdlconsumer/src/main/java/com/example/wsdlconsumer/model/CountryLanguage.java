package com.example.wsdlconsumer.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class CountryLanguage {
    private String isoCode;
    private String name;
}
