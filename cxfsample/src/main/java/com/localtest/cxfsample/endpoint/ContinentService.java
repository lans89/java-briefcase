package com.localtest.cxfsample.endpoint;

import com.localtest.cxfsample.model.Continent;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;

@WebService(serviceName = "ContinentService")
public interface ContinentService {
    @WebMethod
    @WebResult(name = "listContinents")
    List<Continent> listAll();
}
