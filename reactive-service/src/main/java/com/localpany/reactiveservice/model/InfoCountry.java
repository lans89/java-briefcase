package com.localpany.reactiveservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Root(name = "FullCountryInfoResult", strict = false)
public class InfoCountry {
    @Element(name = "sISOCode")
    private String iso;
    @Element(name = "sName")
    private String name;
    @Element(name = "sCapitalCity")
    private String capitalCity;
    @Element(name = "sPhoneCode")
    private Integer phoneCode;
    @Element(name = "sContinentCode")
    private String continentCode;
    @Element(name = "sCurrencyISOCode")
    private String isoCurrency;
    @Element(name = "sCountryFlag")
    private String flag;
}
