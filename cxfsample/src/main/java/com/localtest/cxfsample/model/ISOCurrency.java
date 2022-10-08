package com.localtest.cxfsample.model;

import lombok.Data;

import java.util.Currency;

@Data
public class ISOCurrency {

    private String name;
    private String iso;
    private String symbol;

    public ISOCurrency(Currency currency){
        this.name = currency.getDisplayName();
        this.iso = currency.getCurrencyCode();
        this.symbol = currency.getSymbol();
    }

}
