package com.localtest.cxfsample.model;

import lombok.Builder;
import lombok.Data;

import java.util.Locale;

@Data
public class ISOCountry {
    private String name;
    private String code;
    private String iso;

    public ISOCountry(Locale locale){
        this.name = locale.getDisplayCountry();
        this.iso = locale.getISO3Country();
        this.code = locale.getCountry();
    }
}
