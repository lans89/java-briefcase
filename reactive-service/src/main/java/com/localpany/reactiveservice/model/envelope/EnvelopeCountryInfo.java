package com.localpany.reactiveservice.model.envelope;

import com.localpany.reactiveservice.model.InfoCountry;
import lombok.Data;
import lombok.ToString;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@ToString
@Data
@Root(name = "Envelope")
public class EnvelopeCountryInfo {
    @Element(name = "Body")
    private Body body;

    @ToString
    @Data
    @Root(name = "Body")
    public static class Body{
        @Element(name="FullCountryInfoResponse")
        private InfoCountryResponse response;
    }

    @Data
    @ToString
    @Root(name = "FullCountryInfoResponse")
    public static class InfoCountryResponse{
        @Element(name="FullCountryInfoResult")
        private InfoCountry infoCountry;
    }
}
