package com.example.wsdlconsumer.service;

import com.example.number.client.gen.NumberToWords;
import com.example.number.client.gen.NumberToWordsResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class NumberConversionService extends WebServiceGatewaySupport {
    public Map<String, String> convertToLetters(BigInteger number){
        NumberToWords request = new NumberToWords();
        request.setUbiNum(number);
        NumberToWordsResponse response = (NumberToWordsResponse) getWebServiceTemplate().marshalSendAndReceive(request);
        Map<String, String> result = new HashMap<>();
        result.put("Number", number.toString());
        result.put("Word", response.getNumberToWordsResult());
        return result;

    }
}
