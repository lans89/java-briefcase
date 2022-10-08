package com.localtest.cxfsample.service;

import com.localtest.cxfsample.endpoint.ISONaming;
import com.localtest.cxfsample.model.ISOCountry;
import com.localtest.cxfsample.model.ISOCurrency;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Currency;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ISONamingImpl implements ISONaming {

    private List<Locale> isoCountries;

    private List<Currency> isoCurrency;

    private final String LANG = "es";

    @PostConstruct
    public void init(){
        isoCurrency = new LinkedList<>();
        isoCountries = Arrays.stream(Locale.getISOCountries())
                .map(iso -> {
                    var loc = new Locale("es",iso);
                    var curr = Optional.ofNullable(Currency.getInstance(loc));
                    curr.ifPresent(currency -> isoCurrency.add(currency));
                    return loc;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ISOCountry> allISOCountry() {
        return isoCountries.stream().map(locale -> new ISOCountry(locale)).collect(Collectors.toList());
    }

    @Override
    public List<ISOCurrency> allISOCurrency() {
        return isoCurrency.stream().map(currency -> new ISOCurrency(currency)).collect(Collectors.toList());
    }

    @Override
    public ISOCountry getISOCountryByName(String country) {
        return isoCountries.stream().filter(iso -> iso.getDisplayCountry().equalsIgnoreCase(country))
                .findFirst().map(locale -> new ISOCountry(locale))
                .orElseThrow(() -> new RuntimeException("ISO no encontrado para el pais "+country+"."));
    }

    @Override
    public ISOCurrency getISOCurrencyByName(String currency) {
        return isoCurrency.stream().filter(curr -> curr.getDisplayName().equalsIgnoreCase(currency))
                .findFirst().map(cur -> new ISOCurrency(cur))
                .orElseThrow(() -> new RuntimeException("ISO no encontrado para la moneda "+currency+"."));
    }
}
