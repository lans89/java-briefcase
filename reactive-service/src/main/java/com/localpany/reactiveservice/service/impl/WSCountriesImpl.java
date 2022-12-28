package com.localpany.reactiveservice.service.impl;

import com.localpany.reactiveservice.model.envelope.EnvelopeCountryInfo;
import com.localpany.reactiveservice.model.InfoCountry;
import com.localpany.reactiveservice.properties.WsdlProperties;
import com.localpany.reactiveservice.service.WSCountries;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.text.MessageFormat;

@Service
@Slf4j
public class WSCountriesImpl implements WSCountries {

    @Autowired
    private WsdlProperties wsdlProperties;
    @Autowired
    private WebClient webCountry;

    private final String baseXmlRequest = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://www.oorsprong.org/websamples.countryinfo\">\n" +
            "   <soapenv:Header/>\n" +
            "   <soapenv:Body>\n" +
            "      <web:FullCountryInfo>\n" +
            "         <web:sCountryISOCode>{0}</web:sCountryISOCode>\n" +
            "      </web:FullCountryInfo>\n" +
            "   </soapenv:Body>\n" +
            "</soapenv:Envelope>";

    @Override
    public Mono<InfoCountry> getFullInfoCountry(String codeCountry) {
        return webCountry
                .post().uri(wsdlProperties.getUri())
                .header("SOAPAction", "")
                .bodyValue(MessageFormat.format(baseXmlRequest, codeCountry))
                .retrieve()
                .bodyToMono(String.class).map(body ->{
                    Serializer serializer = new Persister();
                    EnvelopeCountryInfo result = null;
                    try {
                        result = serializer.read(EnvelopeCountryInfo.class, body);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    return result.getBody().getResponse().getInfoCountry();
                });
    }
}
