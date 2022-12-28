package com.localpany.reactiveservice.properties;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class WsdlProperties {
    private String location;
    private String uri;
    private Long timeoutConnect;
    private Long timeoutRead;
    private Long timeoutWrite;
}
