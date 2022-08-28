package com.example.wsdlconsumer.service;

import com.example.country.client.gen.FullCountryInfo;
import com.example.country.client.gen.FullCountryInfoResponse;
import com.example.wsdlconsumer.model.CountryInfoDTO;
import com.example.wsdlconsumer.model.CountryLanguage;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import java.util.stream.Collectors;

public class CountryService extends WebServiceGatewaySupport {
    public CountryInfoDTO getAllInfoCountry(String countryISOCode){
        FullCountryInfo request = new FullCountryInfo();
        request.setSCountryISOCode(countryISOCode);
        FullCountryInfoResponse responseWsdl = (FullCountryInfoResponse)getWebServiceTemplate().marshalSendAndReceive(request);
        return CountryInfoDTO.builder()
                .name(responseWsdl.getFullCountryInfoResult().getSName())
                .capitalCity(responseWsdl.getFullCountryInfoResult().getSCapitalCity())
                .countryFlag(responseWsdl.getFullCountryInfoResult().getSCountryFlag())
                .continentCode(responseWsdl.getFullCountryInfoResult().getSContinentCode())
                .isoCode(responseWsdl.getFullCountryInfoResult().getSISOCode())
                .currencyIsoCode(responseWsdl.getFullCountryInfoResult().getSCurrencyISOCode())
                .phoneCode(responseWsdl.getFullCountryInfoResult().getSPhoneCode())
                .languages(responseWsdl.getFullCountryInfoResult().getLanguages().getTLanguage()
                        .stream().map( x -> CountryLanguage.builder()
                                .name(x.getSName())
                                .isoCode(x.getSISOCode())
                                .build()).collect(Collectors.toList()))
                .build();
    }
}
