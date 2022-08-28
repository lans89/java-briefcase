package com.example.wsdlconsumer.configuration;

import com.example.wsdlconsumer.service.CountryService;
import com.example.wsdlconsumer.service.NumberConversionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.soap.SoapVersion;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;

@Configuration
@Slf4j
public class WsdlConfiguration {

    @Autowired
    private CountryProperties properties;

    @Autowired
    private NumbersProperties propertiesNumber;

    @Bean
    public SaajSoapMessageFactory messageFactory() {
        SaajSoapMessageFactory messageFactory = new SaajSoapMessageFactory();
        messageFactory.setSoapVersion(SoapVersion.SOAP_12);
        return messageFactory;
    }

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath(properties.getPackages());
        return marshaller;
    }

    @Bean
    public CountryService getCountryService(Jaxb2Marshaller marshaller, SaajSoapMessageFactory messageFactory){
        CountryService service = new CountryService();
        service.setDefaultUri(properties.getEndpoint());
        service.setMarshaller(marshaller);
        service.setUnmarshaller(marshaller);
        service.setMessageFactory(messageFactory);
        return service;
    }

    @Bean
    public Jaxb2Marshaller marshallerNumber() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath(propertiesNumber.getPackages());
        return marshaller;
    }

    @Bean
    public NumberConversionService getNumberService(Jaxb2Marshaller marshallerNumber, SaajSoapMessageFactory messageFactory){
        NumberConversionService service = new NumberConversionService();
        LOGGER.info(propertiesNumber.toString());
        service.setDefaultUri(propertiesNumber.getEndpoint());
        service.setMarshaller(marshallerNumber);
        service.setUnmarshaller(marshallerNumber);
        service.setMessageFactory(messageFactory);
        return service;
    }

}
