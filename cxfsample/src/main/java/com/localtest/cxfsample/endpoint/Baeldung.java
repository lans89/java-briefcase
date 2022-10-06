package com.localtest.cxfsample.endpoint;

import com.localtest.cxfsample.model.Student;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(serviceName = "GreetingService")
public interface Baeldung {
    @WebMethod
    @WebResult(name = "Greeting")
    String hello(@WebParam(name = "GreetingRequest") String name);

    @WebMethod
    @WebResult(name="Students")
    String register(@WebParam(name ="StudentRequest") Student student);
}
