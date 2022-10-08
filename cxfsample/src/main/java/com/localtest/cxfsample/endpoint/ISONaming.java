package com.localtest.cxfsample.endpoint;

import com.localtest.cxfsample.model.ISOCountry;
import com.localtest.cxfsample.model.ISOCurrency;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;
import java.util.Locale;
@WebService(serviceName = "ISONamingService")
public interface ISONaming {
    @WebMethod
    @WebResult(name = "ISOCountries")
    List<ISOCountry> allISOCountry();
    @WebMethod
    @WebResult(name = "ISOCurrencies")
    List<ISOCurrency> allISOCurrency();
    @WebMethod
    @WebResult(name = "ISOCountry")
    ISOCountry getISOCountryByName(@WebParam(name = "ISOCountryRequest") String country);
    @WebMethod
    @WebResult(name = "ISOCurrency")
    ISOCurrency getISOCurrencyByName(@WebParam(name = "ISOCurrencyRequest")String currency);
}
