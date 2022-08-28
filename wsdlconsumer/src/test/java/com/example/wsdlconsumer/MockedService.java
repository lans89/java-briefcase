package com.example.wsdlconsumer;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MockedService {
    private String resource;
    private String fileBody;
}
